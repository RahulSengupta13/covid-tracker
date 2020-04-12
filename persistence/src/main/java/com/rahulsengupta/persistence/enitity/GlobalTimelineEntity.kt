package com.rahulsengupta.persistence.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GlobalTimeline")
data class GlobalTimelineEntity(
    @PrimaryKey val id: Int = 1,
    val list: List<GlobalTimelineValue>
) {
    data class GlobalTimelineValue(
        val newConfirmed: Int,
        val newRecovered: Int,
        val newDeaths: Int,
        val updatedAt: String
    )
}