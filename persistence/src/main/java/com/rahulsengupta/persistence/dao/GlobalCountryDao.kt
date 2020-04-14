package com.rahulsengupta.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.rahulsengupta.persistence.enitity.GlobalCountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GlobalCountryDao : BaseDao<GlobalCountryEntity> {

    @Query("SELECT * FROM GlobalCountry")
    fun getGlobalCountryResult(): Flow<List<GlobalCountryEntity>?>


    @Query("SELECT COUNT(*) FROM GlobalCountry")
    fun getGlobalCountryCount(): Int
}