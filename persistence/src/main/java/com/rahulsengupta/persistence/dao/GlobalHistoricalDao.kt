package com.rahulsengupta.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.rahulsengupta.persistence.enitity.GlobalHistoricalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GlobalHistoricalDao : BaseDao<GlobalHistoricalEntity> {

    @Query("SELECT * FROM GlobalHistorical")
    fun getGlobalHistorical(): Flow<GlobalHistoricalEntity?>


    @Query("SELECT COUNT(*) FROM GlobalHistorical")
    fun getGlobalHistoricalCount(): Int
}