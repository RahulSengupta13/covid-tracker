package com.rahulsengupta.network.services

import com.rahulsengupta.network.model.response.GlobalCountryResponseItem
import com.rahulsengupta.network.model.response.GlobalHistoricalResponse
import com.rahulsengupta.network.model.response.GlobalTotalsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NovelCovid19Service {

    companion object {
        const val BASE_URL = "https://corona.lmao.ninja/v2/"
    }

    @GET("all")
    suspend fun getGlobalTotals(): Response<GlobalTotalsResponse>

    @GET("historical/all")
    suspend fun getGlobalHistorical(
        @Query("lastDays") lastDays: Int
    ): Response<GlobalHistoricalResponse>

    @GET("countries")
    suspend fun getGlobalCountryResult(
        @Query("sort") sort: String
    ): Response<List<GlobalCountryResponseItem>>
}