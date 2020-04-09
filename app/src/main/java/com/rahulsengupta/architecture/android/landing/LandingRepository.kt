package com.rahulsengupta.architecture.android.landing

import com.rahulsengupta.architecture.android.core.datasource.TypiCodeDataSource
import com.rahulsengupta.network.data.Result
import com.rahulsengupta.network.model.response.GlobalTotalsResponse
import javax.inject.Inject

interface ILandingRepository {
    suspend fun getPosts(): Result<GlobalTotalsResponse>
}

class LandingRepository @Inject constructor(
    private val dataSource: TypiCodeDataSource
) : ILandingRepository {

    override suspend fun getPosts() = dataSource.getPosts()
}