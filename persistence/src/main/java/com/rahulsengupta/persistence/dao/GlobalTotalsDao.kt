package com.rahulsengupta.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.rahulsengupta.persistence.enitity.GlobalTotalsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GlobalTotalsDao : BaseDao<GlobalTotalsEntity> {

    @Query("SELECT * FROM GlobalTotals")
    fun getGlobalTotals(): Flow<List<GlobalTotalsEntity>>
}