package com.rahulsengupta.network.usecase

import com.rahulsengupta.network.datasource.NewsServiceDataSource
import com.rahulsengupta.network.model.response.TopHeadlinesResponse
import javax.inject.Inject

interface IFetchHeadlinesUseCase {
    suspend fun fetchHeadlines(
        query: String,
        sortBy: String,
        language: String,
        pageSize: Int,
        page: Int,
        country: String
    ): TopHeadlinesResponse?
}

class FetchHeadlinesUseCase @Inject constructor(
    private val dataSource: NewsServiceDataSource
) : IFetchHeadlinesUseCase {

    override suspend fun fetchHeadlines(
        query: String,
        sortBy: String,
        language: String,
        pageSize: Int,
        page: Int,
        country: String
    ) = dataSource.getHeadlines(query, sortBy, language, pageSize, page, country).data
}