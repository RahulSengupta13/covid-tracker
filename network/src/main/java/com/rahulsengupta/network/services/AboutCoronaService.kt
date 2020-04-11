package com.rahulsengupta.network.services

import com.rahulsengupta.network.model.response.CoronaTimelineResponse
import retrofit2.Response
import retrofit2.http.GET

interface AboutCoronaService {

    companion object {
        const val BASE_URL = "https://corona-api.com/"
    }

    @GET("timeline")
    suspend fun getTimeline(): Response<CoronaTimelineResponse>
}