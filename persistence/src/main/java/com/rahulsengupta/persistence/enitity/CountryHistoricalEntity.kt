package com.rahulsengupta.persistence.enitity

import androidx.room.Entity

@Entity(tableName = "CountryHistoricalEntity", primaryKeys = ["country", "province"])
data class CountryHistoricalEntity(
    val country: String,
    val province: String,
    val cases: Map<String, Int>,
    val deaths: Map<String, Int>,
    val recovered: Map<String, Int>
)