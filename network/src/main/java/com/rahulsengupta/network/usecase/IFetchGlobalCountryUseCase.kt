package com.rahulsengupta.network.usecase

import com.rahulsengupta.network.datasource.NovelCovid19DataSource
import com.rahulsengupta.network.model.response.GlobalCountryResponseItem
import javax.inject.Inject

interface IFetchGlobalCountryUseCase {
    suspend fun fetchGlobalCountry(sortBy: String): List<GlobalCountryResponseItem>?
}

class FetchGlobalCountryUseCase @Inject constructor(
    private val dataSource: NovelCovid19DataSource
) : IFetchGlobalCountryUseCase {

    override suspend fun fetchGlobalCountry(sortBy: String) = dataSource.getGlobalCountryResult(sortBy).data
}