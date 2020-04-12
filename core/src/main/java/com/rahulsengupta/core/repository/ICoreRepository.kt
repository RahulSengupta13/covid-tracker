package com.rahulsengupta.core.repository

import com.rahulsengupta.core.base.CoroutineRepository
import com.rahulsengupta.core.di.ICoroutinesDispatcher
import com.rahulsengupta.core.extensions.getFormattedDateFromShortPattern
import com.rahulsengupta.core.extensions.getFormattedDateFromUTCTimestamp
import com.rahulsengupta.network.datasource.AboutCoronaDataSource
import com.rahulsengupta.network.datasource.NewsServiceDataSource
import com.rahulsengupta.network.datasource.NovelCovid19DataSource
import com.rahulsengupta.persistence.dao.GlobalHistoricalDao
import com.rahulsengupta.persistence.dao.GlobalTimelineDao
import com.rahulsengupta.persistence.dao.GlobalTotalsDao
import com.rahulsengupta.persistence.dao.HeadlinesDao
import com.rahulsengupta.persistence.enitity.ArticleEntity
import com.rahulsengupta.persistence.enitity.GlobalHistoricalEntity
import com.rahulsengupta.persistence.enitity.GlobalTimelineEntity
import com.rahulsengupta.persistence.enitity.GlobalTotalsEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface ICoreRepository {

    fun initializeAsync()

    val initialized: Flow<Unit>

}

class CoreRepository @Inject constructor(
    private val novelCovid: NovelCovid19DataSource,
    private val aboutCorona: AboutCoronaDataSource,
    private val news: NewsServiceDataSource,
    private val globalTotalsDao: GlobalTotalsDao,
    private val globalHistoricalDao: GlobalHistoricalDao,
    private val globalTimelineDao: GlobalTimelineDao,
    private val headlinesDao: HeadlinesDao,
    private val dispatcher: ICoroutinesDispatcher
) : ICoreRepository, CoroutineRepository(dispatcher) {

    private val channel = ConflatedBroadcastChannel<Unit>()
    override val initialized: Flow<Unit>
        get() = channel.asFlow()

    @ExperimentalCoroutinesApi
    override fun initializeAsync() {
        CoroutineScope(dispatcher.IO).launch {

            val hasGlobalTotals = globalTotalsDao.getGlobalTotalsCount() > 0
            val hasGlobalHistorical = globalHistoricalDao.getGlobalHistoricalCount() > 0
            val hasGlobalTimeline = globalTimelineDao.getGlobalTimelineCount() > 0
            val hasHeadlines = headlinesDao.getHeadlinesCount() > 0

            if (hasGlobalTimeline && hasGlobalHistorical && hasGlobalTotals && hasHeadlines) {
                channel.send(Unit)
                initializeGlobalTotals()
                initializeGlobalHistorical()
                initializeGlobalTimeline()
                initializeTopHeadlines()
            } else {
                val deferreds = listOf(
                    initializeGlobalTotalsAsync(),
                    initializeGlobalHistoricalAsync(),
                    initializeGlobalTimelineAsync(),
                    initializeTopHeadlinesAsync()
                )
                deferreds.awaitAll()
                channel.send(Unit)
            }
        }
    }

    private suspend fun initializeTopHeadlinesAsync() = withContext(dispatcher.IO) {
        async {
            initializeTopHeadlines()
        }
    }

    private suspend fun initializeTopHeadlines() {
        val headlines = news.getHeadlines(
            "COVID",
            "publishedAt",
            "en",
            100,
            1,
            "us"
        ).data ?: return
        val articles = headlines.articles.map {
            ArticleEntity(
                it.author,
                it.content,
                it.description,
                it.publishedAt,
                ArticleEntity.Source(
                    it.source?.id,
                    it.source?.name
                ),
                it.title,
                it.url,
                it.urlToImage
            )
        }
        headlinesDao.insertAllOrReplace(articles)
    }

    private suspend fun initializeGlobalTotalsAsync() = withContext(dispatcher.IO) {
        async {
            initializeGlobalTotals()
        }
    }

    private suspend fun initializeGlobalTotals() {
        val globalTotals = novelCovid.getGlobalTotals().data ?: return
        val globalTotalsEntity = GlobalTotalsEntity(
            globalTotals.active,
            globalTotals.affectedCountries,
            globalTotals.cases,
            globalTotals.casesPerOneMillion,
            globalTotals.critical,
            globalTotals.deaths,
            globalTotals.deathsPerOneMillion,
            globalTotals.recovered,
            globalTotals.tests,
            globalTotals.testsPerOneMillion,
            globalTotals.todayCases,
            globalTotals.todayDeaths,
            globalTotals.updated
        )
        globalTotalsDao.insertOrReplace(item = globalTotalsEntity)
    }

    private suspend fun initializeGlobalHistoricalAsync() = withContext(dispatcher.IO) {
        async {
            initializeGlobalHistorical()
        }
    }

    private suspend fun initializeGlobalHistorical() {
        val globalHistorical = novelCovid.getGlobalHistorical(30).data ?: return
        val globalHistoricalEntity = GlobalHistoricalEntity(
            cases = globalHistorical.cases.map { (k, v) -> k.getFormattedDateFromShortPattern() to v }
                .toMap(),
            deaths = globalHistorical.deaths.map { (k, v) -> k.getFormattedDateFromShortPattern() to v }
                .toMap(),
            recovered = globalHistorical.recovered.map { (k, v) -> k.getFormattedDateFromShortPattern() to v }
                .toMap()
        )
        globalHistoricalDao.insertOrReplace(globalHistoricalEntity)
    }

    private suspend fun initializeGlobalTimelineAsync() = withContext(dispatcher.IO) {
        async {
            initializeGlobalTimeline()
        }
    }

    private suspend fun initializeGlobalTimeline() {
        val globalTimeline = aboutCorona.getTimeline().data ?: return
        val globalTimelineEntity = GlobalTimelineEntity(
            list = globalTimeline.data.map {
                GlobalTimelineEntity.GlobalTimelineValue(
                    it.newConfirmed,
                    it.newRecovered,
                    it.newDeaths,
                    it.updatedAt.getFormattedDateFromUTCTimestamp()
                )
            }.asReversed()
        )
        globalTimelineDao.insertOrReplace(globalTimelineEntity)
    }
}