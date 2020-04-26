package com.rahulsengupta.network.usecase

import com.rahulsengupta.network.datasource.AboutCoronaDataSource
import com.rahulsengupta.network.model.response.CoronaTimelineResponse
import javax.inject.Inject

interface IFetchGlobalTimelineUseCase {
    suspend fun fetchGlobalTimeline(): CoronaTimelineResponse?
}

class FetchGlobalTimelineUseCase @Inject constructor(
    private val dataSource: AboutCoronaDataSource
) : IFetchGlobalTimelineUseCase {

    override suspend fun fetchGlobalTimeline() = dataSource.getTimeline().data
}