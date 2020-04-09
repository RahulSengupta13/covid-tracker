package com.rahulsengupta.architecture.android.landing

import com.rahulsengupta.architecture.android.core.datasource.TypiCodeDataSource
import com.rahulsengupta.network.model.response.Post
import com.rahulsengupta.architecture.android.core.data.Result
import javax.inject.Inject

interface ILandingRepository {
    suspend fun getPosts(): Result<List<Post>>
}

class LandingRepository @Inject constructor(
    private val dataSource: TypiCodeDataSource
) : ILandingRepository {

    override suspend fun getPosts() = dataSource.getPosts()
}