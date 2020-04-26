package com.rahulsengupta.network.usecase

import com.rahulsengupta.network.datasource.NovelCovid19DataSource
import com.rahulsengupta.network.model.response.GlobalHistoricalResponse
import javax.inject.Inject

interface IFetchGlobalHistoricalUseCase {
    suspend fun fetchGlobalHistorical(lastDays: Int): GlobalHistoricalResponse?
}

class FetchGlobalHistoricalUseCase @Inject constructor(
    private val dataSource: NovelCovid19DataSource
) : IFetchGlobalHistoricalUseCase {

    override suspend fun fetchGlobalHistorical(lastDays: Int) = dataSource.getGlobalHistorical(lastDays).data
}