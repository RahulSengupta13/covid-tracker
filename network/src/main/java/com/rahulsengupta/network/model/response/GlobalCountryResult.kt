package com.rahulsengupta.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GlobalCountryResponseItem(
    val active: Int,
    val cases: Int,
    val casesPerOneMillion: Float,
    val country: String,
    val countryInfo: CountryInfo,
    val critical: Int,
    val deaths: Int,
    val deathsPerOneMillion: Float,
    val recovered: Int,
    val todayCases: Int,
    val todayDeaths: Int,
    val updated: Long
) {
    @Serializable
    data class CountryInfo(
        val flag: String? = null,
        @SerialName("_id")
        val id: Int? = null,
        val iso2: String? = null,
        val iso3: String? = null,
        val lat: Float? = 0F,
        val long: Float? = 0F
    )
}
