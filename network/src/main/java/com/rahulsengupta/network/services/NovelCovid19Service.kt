package com.rahulsengupta.network.services

import com.rahulsengupta.network.model.response.GlobalTotalsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NovelCovid19Service {

    companion object {
        const val BASE_URL = "https://corona.lmao.ninja/"
    }

    @GET("all")
    suspend fun getGlobalTotals(): Response<GlobalTotalsResponse>
}