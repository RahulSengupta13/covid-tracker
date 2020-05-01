package com.rahulsengupta.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rahulsengupta.persistence.converter.DbTypeConverters
import com.rahulsengupta.persistence.dao.*
import com.rahulsengupta.persistence.enitity.*
import kotlinx.serialization.json.Json

object DatabaseMeta {
    const val NAME = "covid_tracker.db"
    const val VERSION = 3
}

@Database(
    entities = [
        GlobalTotalsEntity::class,
        GlobalHistoricalEntity::class,
        GlobalTimelineEntity::class,
        ArticleEntity::class,
        GlobalCountryEntity::class,
        CountryHistoricalEntity::class
    ],
    version = DatabaseMeta.VERSION,
    exportSchema = false
)
@TypeConverters(value = [DbTypeConverters::class])
abstract class CovidTrackerApplicationDatabase : RoomDatabase() {
    companion object {
        lateinit var json: Json
    }
    abstract fun globalTotalDao(): GlobalTotalsDao
    abstract fun globalHistoricalDao(): GlobalHistoricalDao
    abstract fun globalTimelineDao(): GlobalTimelineDao
    abstract fun headlinesDao(): HeadlinesDao
    abstract fun globalCountryDao(): GlobalCountryDao
    abstract fun countryHistoricalDao(): CountryHistoricalDao
}