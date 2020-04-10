package com.rahulsengupta.network.services

import com.rahulsengupta.network.model.response.GlobalHistoricalResponse
import com.rahulsengupta.network.model.response.GlobalTotalsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NovelCovid19Service {

    companion object {
        const val BASE_URL = "https://corona.lmao.ninja/"
    }

    @GET("all")
    suspend fun getGlobalTotals(): Response<GlobalTotalsResponse>

    @GET("v2/historical/all")
    suspend fun getGlobalHistorical(
        @Query("lastDays") lastDays: Int
    ): Response<GlobalHistoricalResponse>
}