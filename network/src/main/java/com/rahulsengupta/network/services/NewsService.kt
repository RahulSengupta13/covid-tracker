package com.rahulsengupta.network.services

import com.rahulsengupta.network.model.response.TopHeadlinesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("top-headlines")
    suspend fun topHeadlines(
        @Query("q") q: String,
        @Query("sortBy") sortBy: String,
        @Query("language") language: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Response<TopHeadlinesResponse>
}