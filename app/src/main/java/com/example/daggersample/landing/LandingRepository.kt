package com.example.daggersample.landing

import com.example.daggersample.core.data.Result
import com.example.daggersample.core.network.TypiCodeDataSource
import com.example.daggersample.core.network.model.response.Post
import javax.inject.Inject

interface ILandingRepository {
    suspend fun getPosts(): Result<List<Post>>
}

class LandingRepository @Inject constructor(
    private val dataSource: TypiCodeDataSource
) : ILandingRepository {

    override suspend fun getPosts() = dataSource.getPosts()
}