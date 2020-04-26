package com.rahulsengupta.network.datasource

import com.rahulsengupta.network.base.BaseDataSource
import com.rahulsengupta.network.services.NewsService
import javax.inject.Inject

class NewsServiceDataSource @Inject constructor(
    private val newsApi: NewsService
) : BaseDataSource() {

    suspend fun getHeadlines(
        query: String,
        sortBy: String,
        language: String,
        pageSize: Int,
        page: Int,
        country: String
    ) = getResult { newsApi.topHeadlines(query, sortBy, language, pageSize, page, country) }

}