package com.rahulsengupta.network.usecase

import com.rahulsengupta.network.datasource.NovelCovid19DataSource
import com.rahulsengupta.network.model.response.CountryHistoricalResponse
import javax.inject.Inject

interface IFetchCountriesHistoricalUseCase {
    suspend fun fetchCountriesHistorical(lastDays: Int): List<CountryHistoricalResponse>?
}

class FetchCountriesHistoricalUseCase @Inject constructor(
    private val dataSource: NovelCovid19DataSource
) : IFetchCountriesHistoricalUseCase {

    override suspend fun fetchCountriesHistorical(lastDays: Int) = dataSource.getCountriesHistorical(lastDays).data
}