package com.rahulsengupta.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class GlobalTotalsResponse(
    val active: Int,
    val affectedCountries: Int,
    val cases: Int,
    val casesPerOneMillion: Float,
    val critical: Int,
    val deaths: Int,
    val deathsPerOneMillion: Float,
    val recovered: Int,
    val tests: Int,
    val testsPerOneMillion: Float,
    val todayCases: Int,
    val todayDeaths: Int,
    val updated: Long
)