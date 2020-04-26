package com.rahulsengupta.core.usecase

import javax.inject.Inject

interface IGetIsDataDownloadedUseCase {
    suspend fun get(): Boolean
}

class GetIsDataDownloadedUseCase @Inject constructor(
    private val loadGlobalTimelineUseCase: ILoadGlobalTimelineUseCase,
    private val loadGlobalHistoricalUseCase: ILoadGlobalHistoricalUseCase,
    private val loadCountriesHistoricalUseCase: ILoadCountriesHistoricalUseCase,
    private val loadGlobalCountryUseCase: ILoadGlobalCountryUseCase,
    private val loadGlobalTotalsUseCase: ILoadGlobalTotalsUseCase,
    private val loadHeadlinesUseCase: ILoadHeadlinesUseCase
) : IGetIsDataDownloadedUseCase {

    override suspend fun get(): Boolean {
        val hasGlobalTotals = loadGlobalTotalsUseCase.hasGlobalTotals()
        val hasGlobalHistorical = loadGlobalHistoricalUseCase.hasGlobalHistorical()
        val hasGlobalTimeline = loadGlobalTimelineUseCase.hasGlobalTimeline()
        val hasHeadlines = loadHeadlinesUseCase.hasHeadlines()
        val hasGlobalCountryResult = loadGlobalCountryUseCase.hasGlobalCountry()
        val hasCountryHistoricalResult = loadCountriesHistoricalUseCase.hasCountriesHistorical()

        return hasGlobalTimeline && hasGlobalHistorical && hasGlobalTotals && hasHeadlines && hasGlobalCountryResult && hasCountryHistoricalResult
    }
}