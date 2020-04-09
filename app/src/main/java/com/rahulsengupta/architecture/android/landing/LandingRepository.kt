package com.rahulsengupta.architecture.android.landing

import com.rahulsengupta.architecture.android.core.datasource.NovelCovid19DataSource
import com.rahulsengupta.network.data.Result
import com.rahulsengupta.network.model.response.GlobalTotalsResponse
import javax.inject.Inject

interface ILandingRepository {
    suspend fun getPosts(): Result<GlobalTotalsResponse>
}

class LandingRepository @Inject constructor(
    private val dataSource: NovelCovid19DataSource
) : ILandingRepository {

    override suspend fun getPosts() = dataSource.getPosts()
}