package com.rahulsengupta.persistence.di

import android.app.Application
import androidx.room.Room
import com.rahulsengupta.persistence.CovidTrackerApplicationDatabase
import com.rahulsengupta.persistence.DatabaseMeta
import com.rahulsengupta.persistence.dao.*
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application, json: Json): CovidTrackerApplicationDatabase {
        return Room.databaseBuilder(
            application,
            CovidTrackerApplicationDatabase::class.java,
            DatabaseMeta.NAME
        ).fallbackToDestructiveMigration().build().apply {
            CovidTrackerApplicationDatabase.json = json
        }
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json(JsonConfiguration.Stable)

    @Provides
    @Singleton
    fun provideGlobalTotalsDao(database: CovidTrackerApplicationDatabase): GlobalTotalsDao {
        return database.globalTotalDao()
    }

    @Provides
    @Singleton
    fun provideGlobalHistoricalDao(database: CovidTrackerApplicationDatabase): GlobalHistoricalDao {
        return database.globalHistoricalDao()
    }

    @Provides
    @Singleton
    fun provideGlobalTimelineDao(database: CovidTrackerApplicationDatabase): GlobalTimelineDao {
        return database.globalTimelineDao()
    }

    @Provides
    @Singleton
    fun provideHeadlineDao(database: CovidTrackerApplicationDatabase): HeadlinesDao {
        return database.headlinesDao()
    }

    @Provides
    @Singleton
    fun provideGlobalCountryDao(database: CovidTrackerApplicationDatabase): GlobalCountryDao {
        return database.globalCountryDao()
    }

    @Provides
    @Singleton
    fun provideCountryHistoricalDao(database: CovidTrackerApplicationDatabase): CountryHistoricalDao {
        return database.countryHistoricalDao()
    }
}