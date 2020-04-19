package com.rahulsengupta.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.rahulsengupta.persistence.enitity.CountryHistoricalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryHistoricalDao : BaseDao<CountryHistoricalEntity> {

    @Query("SELECT * FROM CountryHistoricalEntity")
    fun getCountryHistorical(): Flow<List<CountryHistoricalEntity>?>

    @Query("SELECT COUNT(*) FROM CountryHistoricalEntity")
    fun getCountryHistoricalCount(): Int
}