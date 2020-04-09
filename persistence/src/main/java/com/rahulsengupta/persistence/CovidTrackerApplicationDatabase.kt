package com.rahulsengupta.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rahulsengupta.persistence.dao.GlobalTotalsDao
import com.rahulsengupta.persistence.enitity.GlobalTotalsEntity

object DatabaseMeta {
    const val NAME = "covid_tracker.db"
    const val VERSION = 1
}

@Database(
    entities = [
        GlobalTotalsEntity::class
    ],
    version = DatabaseMeta.VERSION
)
abstract class CovidTrackerApplicationDatabase : RoomDatabase() {
    abstract fun globalTotalDao(): GlobalTotalsDao
}