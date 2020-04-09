package com.rahulsengupta.persistence.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GlobalTotals")
data class GlobalTotalsEntity(
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
    @PrimaryKey val updated: Long
)