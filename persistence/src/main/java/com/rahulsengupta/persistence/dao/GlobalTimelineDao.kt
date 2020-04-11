package com.rahulsengupta.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.rahulsengupta.persistence.enitity.GlobalTimelineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GlobalTimelineDao : BaseDao<GlobalTimelineEntity> {

    @Query("SELECT * FROM GlobalTimeline")
    fun getGlobalTimeline(): Flow<GlobalTimelineEntity>
}