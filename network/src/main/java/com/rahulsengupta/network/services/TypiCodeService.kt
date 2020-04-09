package com.rahulsengupta.network.services

import com.rahulsengupta.network.model.response.Post
import retrofit2.Response
import retrofit2.http.GET

interface TypiCodeService {

    companion object {
        const val BASE_URL = "http://jsonplaceholder.typicode.com/"
    }

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}