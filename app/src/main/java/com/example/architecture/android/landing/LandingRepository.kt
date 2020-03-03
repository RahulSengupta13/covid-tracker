package com.example.architecture.android.landing

import com.example.architecture.android.core.data.Result
import com.example.architecture.android.core.datasource.TypiCodeDataSource
import com.example.network.model.response.Post
import javax.inject.Inject

interface ILandingRepository {
    suspend fun getPosts(): Result<List<Post>>
}

class LandingRepository @Inject constructor(
    private val dataSource: TypiCodeDataSource
) : ILandingRepository {

    override suspend fun getPosts() = dataSource.getPosts()
}