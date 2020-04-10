package com.rahulsengupta.persistence.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GlobalHistorical")
data class GlobalHistoricalEntity(
    @PrimaryKey val id: Int = 1,
    val cases: Map<String, Int>,
    val deaths: Map<String, Int>,
    val recovered: Map<String, Int>
)