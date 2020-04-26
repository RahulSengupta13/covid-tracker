package com.rahulsengupta.core.repository

import com.rahulsengupta.core.base.CoroutineRepository
import com.rahulsengupta.core.di.ICoroutinesDispatcher
import com.rahulsengupta.core.usecase.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

interface ICoreRepository {

    fun initialize()

    val initialized: Flow<Unit>
}

class CoreRepository @Inject constructor(
    private val loadGlobalTimelineUseCase: ILoadGlobalTimelineUseCase,
    private val loadGlobalHistoricalUseCase: ILoadGlobalHistoricalUseCase,
    private val loadCountriesHistoricalUseCase: ILoadCountriesHistoricalUseCase,
    private val loadGlobalCountryUseCase: ILoadGlobalCountryUseCase,
    private val loadGlobalTotalsUseCase: ILoadGlobalTotalsUseCase,
    private val loadHeadlinesUseCase: ILoadHeadlinesUseCase,
    private val dispatcher: ICoroutinesDispatcher
) : ICoreRepository, CoroutineRepository(dispatcher) {

    private val channel = ConflatedBroadcastChannel<Unit>()
    override val initialized: Flow<Unit>
        get() = channel.asFlow()

    @ExperimentalCoroutinesApi
    override fun initialize() {
        CoroutineScope(dispatcher.IO).launch {

            val hasGlobalTotals = loadGlobalTotalsUseCase.hasGlobalTotals()
            val hasGlobalHistorical = loadGlobalHistoricalUseCase.hasGlobalHistorical()
            val hasGlobalTimeline = loadGlobalTimelineUseCase.hasGlobalTimeline()
            val hasHeadlines = loadHeadlinesUseCase.hasHeadlines()
            val hasGlobalCountryResult = loadGlobalCountryUseCase.hasGlobalCountry()
            val hasCountryHistoricalResult = loadCountriesHistoricalUseCase.hasCountriesHistorical()

            if (hasGlobalTimeline && hasGlobalHistorical && hasGlobalTotals && hasHeadlines && hasGlobalCountryResult && hasCountryHistoricalResult) {
                channel.send(Unit)
                initializeGlobalTotals()
                initializeGlobalHistorical()
                initializeGlobalTimeline()
                initializeTopHeadlines()
                initializeGlobalCountryResult()
                initializeCountriesHistorical()
            } else {
                val deferreds = listOf(
                    initializeGlobalTotalsAsync(),
                    initializeGlobalHistoricalAsync(),
                    initializeGlobalTimelineAsync(),
                    initializeTopHeadlinesAsync(),
                    initializeGlobalCountryResultAsync(),
                    initializeCountriesHistoricalAsync()
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
        loadHeadlinesUseCase.loadHeadlines("COVID", "publishedAt", "en", 100, 1, "")
    }

    private suspend fun initializeGlobalTotalsAsync() = withContext(dispatcher.IO) {
        async {
            initializeGlobalTotals()
        }
    }

    private suspend fun initializeGlobalTotals() {
        loadGlobalTotalsUseCase.loadGlobalTotals()
    }

    private suspend fun initializeGlobalCountryResultAsync() = withContext(dispatcher.IO) {
        async {
            initializeGlobalCountryResult()
        }
    }

    private suspend fun initializeGlobalCountryResult() {
        loadGlobalCountryUseCase.loadGlobalCountry("todayCases")
    }

    private suspend fun initializeCountriesHistoricalAsync() = withContext(dispatcher.IO) {
        async {
            initializeCountriesHistorical()
        }
    }

    private suspend fun initializeCountriesHistorical() {
        loadCountriesHistoricalUseCase.loadCountriesHistorical(30)
    }

    private suspend fun initializeGlobalHistoricalAsync() = withContext(dispatcher.IO) {
        async {
            initializeGlobalHistorical()
        }
    }

    private suspend fun initializeGlobalHistorical() =
        loadGlobalHistoricalUseCase.loadGlobalHistorical(30)

    private suspend fun initializeGlobalTimelineAsync() = withContext(dispatcher.IO) {
        async {
            initializeGlobalTimeline()
        }
    }

    private suspend fun initializeGlobalTimeline() = loadGlobalTimelineUseCase.loadGlobalTimeline()
}