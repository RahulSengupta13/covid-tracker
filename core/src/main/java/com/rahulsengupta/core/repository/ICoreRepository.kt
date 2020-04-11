package com.rahulsengupta.core.repository

import com.rahulsengupta.core.di.ICoroutinesDispatcher
import com.rahulsengupta.core.extensions.getFormattedDateFromShortPattern
import com.rahulsengupta.core.extensions.getFormattedDateFromUTCTimestamp
import com.rahulsengupta.network.datasource.AboutCoronaDataSource
import com.rahulsengupta.network.datasource.NovelCovid19DataSource
import com.rahulsengupta.persistence.dao.GlobalHistoricalDao
import com.rahulsengupta.persistence.dao.GlobalTimelineDao
import com.rahulsengupta.persistence.dao.GlobalTotalsDao
import com.rahulsengupta.persistence.enitity.GlobalHistoricalEntity
import com.rahulsengupta.persistence.enitity.GlobalTimelineEntity
import com.rahulsengupta.persistence.enitity.GlobalTotalsEntity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ICoreRepository {

    suspend fun initializeAsync(): Deferred<Unit>

}

class CoreRepository @Inject constructor(
    private val novelCovid: NovelCovid19DataSource,
    private val aboutCorona: AboutCoronaDataSource,
    private val globalTotalsDao: GlobalTotalsDao,
    private val globalHistoricalDao: GlobalHistoricalDao,
    private val globalTimelineDao: GlobalTimelineDao,
    private val dispatcher: ICoroutinesDispatcher
) : ICoreRepository {

    override suspend fun initializeAsync() = withContext(dispatcher.IO) {
        async {
            initializeGlobalTotalsAsync().await()
            initializeGlobalHistoricalAsync().await()
            initializeGlobalTimelineAsync().await()
        }
    }

    private suspend fun initializeGlobalTotalsAsync() = withContext(dispatcher.IO) {
        async {
            val globalTotals = novelCovid.getGlobalTotals().data ?: return@async
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
    }

    private suspend fun initializeGlobalHistoricalAsync() = withContext(dispatcher.IO) {
        async {
            val globalHistorical = novelCovid.getGlobalHistorical(30).data ?: return@async
            val globalHistoricalEntity = GlobalHistoricalEntity(
                cases = globalHistorical.cases.map { (k, v) -> k.getFormattedDateFromShortPattern() to v}.toMap(),
                deaths = globalHistorical.deaths.map { (k, v) -> k.getFormattedDateFromShortPattern() to v}.toMap(),
                recovered = globalHistorical.recovered.map { (k, v) -> k.getFormattedDateFromShortPattern() to v}.toMap()
            )
            globalHistoricalDao.insertOrReplace(globalHistoricalEntity)
        }
    }

    private suspend fun initializeGlobalTimelineAsync() = withContext(dispatcher.IO) {
        async {
            val globalTimeline = aboutCorona.getTimeline().data ?: return@async
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
}