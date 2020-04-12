package com.rahulsengupta.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rahulsengupta.persistence.converter.DbTypeConverters
import com.rahulsengupta.persistence.dao.GlobalHistoricalDao
import com.rahulsengupta.persistence.dao.GlobalTimelineDao
import com.rahulsengupta.persistence.dao.GlobalTotalsDao
import com.rahulsengupta.persistence.dao.HeadlinesDao
import com.rahulsengupta.persistence.enitity.ArticleEntity
import com.rahulsengupta.persistence.enitity.GlobalHistoricalEntity
import com.rahulsengupta.persistence.enitity.GlobalTimelineEntity
import com.rahulsengupta.persistence.enitity.GlobalTotalsEntity

object DatabaseMeta {
    const val NAME = "covid_tracker.db"
    const val VERSION = 2
}

@Database(
    entities = [
        GlobalTotalsEntity::class,
        GlobalHistoricalEntity::class,
        GlobalTimelineEntity::class,
        ArticleEntity::class
    ],
    version = DatabaseMeta.VERSION,
    exportSchema = false
)
@TypeConverters(value = [DbTypeConverters::class])
abstract class CovidTrackerApplicationDatabase : RoomDatabase() {
    abstract fun globalTotalDao(): GlobalTotalsDao
    abstract fun globalHistoricalDao(): GlobalHistoricalDao
    abstract fun globalTimelineDao(): GlobalTimelineDao
    abstract fun headlinesDao(): HeadlinesDao
}