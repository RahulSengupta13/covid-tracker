package com.rahulsengupta.persistence.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GlobalCountry")
data class GlobalCountryEntity(
    val active: Int,
    val cases: Int,
    val casesPerOneMillion: Float,
    @PrimaryKey
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
    data class CountryInfo(
        val flag: String? = "",
        val id: Int? = 0,
        val iso2: String? = "",
        val iso3: String? = "",
        val lat: Float? = 0F,
        val long: Float? = 0F
    )
}
