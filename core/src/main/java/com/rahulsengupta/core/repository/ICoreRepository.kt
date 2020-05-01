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
    private val isDataDownloadedUseCase: IGetIsDataDownloadedUseCase,
    private val dispatcher: ICoroutinesDispatcher
) : ICoreRepository, CoroutineRepository(dispatcher) {

    private val channel = ConflatedBroadcastChannel<Unit>()
    override val initialized: Flow<Unit>
        get() = channel.asFlow()

    @ExperimentalCoroutinesApi
    override fun initialize() {
        CoroutineScope(dispatcher.IO).launch {
            val isDataDownloaded = isDataDownloadedUseCase.get()
            if (isDataDownloaded) {
                //move past splash and download data in background
                channel.send(Unit)
            }

            listOf(
                async { initializeGlobalTotals() },
                async { initializeGlobalHistorical() },
                async { initializeGlobalTimeline() },
                async { initializeTopHeadlines() },
                async { initializeGlobalCountryResult() },
                async { initializeCountriesHistorical() }
            ).awaitAll()
            channel.send(Unit)
        }
    }

    private suspend fun initializeTopHeadlines() {
        loadHeadlinesUseCase.loadHeadlines("COVID", "publishedAt", "en", 100, 1, "")
    }

    private suspend fun initializeGlobalTotals() {
        loadGlobalTotalsUseCase.loadGlobalTotals()
    }

    private suspend fun initializeGlobalCountryResult() {
        loadGlobalCountryUseCase.loadGlobalCountry("todayCases")
    }

    private suspend fun initializeCountriesHistorical() {
        loadCountriesHistoricalUseCase.loadCountriesHistorical(30)
    }

    private suspend fun initializeGlobalHistorical() {
        loadGlobalHistoricalUseCase.loadGlobalHistorical(30)
    }

    private suspend fun initializeGlobalTimeline() {
        loadGlobalTimelineUseCase.loadGlobalTimeline()
    }
}