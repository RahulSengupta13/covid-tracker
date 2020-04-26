package com.rahulsengupta.network.usecase

import com.rahulsengupta.network.datasource.NovelCovid19DataSource
import com.rahulsengupta.network.model.response.GlobalTotalsResponse
import javax.inject.Inject

interface IFetchGlobalTotalsUseCase {
    suspend fun fetchGlobalTotals(): GlobalTotalsResponse?
}

class FetchGlobalTotalsUseCase @Inject constructor(
    private val dataSource: NovelCovid19DataSource
) : IFetchGlobalTotalsUseCase {

    override suspend fun fetchGlobalTotals() = dataSource.getGlobalTotals().data
}