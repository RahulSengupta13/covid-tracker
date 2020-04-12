package com.rahulsengupta.network.datasource

import com.rahulsengupta.network.base.BaseDataSource
import com.rahulsengupta.network.services.NewsService
import javax.inject.Inject

class NewsServiceDataSource @Inject constructor(
    private val newsApi: NewsService
) : BaseDataSource() {

    suspend fun getHeadlines(
        q: String,
        sortBy: String,
        language: String,
        pageSize: Int,
        page: Int
    ) = getResult { newsApi.topHeadlines(q, sortBy, language, pageSize, page) }

}