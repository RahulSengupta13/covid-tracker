package com.rahulsengupta.persistence.di

import android.app.Application
import androidx.room.Room
import com.rahulsengupta.persistence.CovidTrackerApplicationDatabase
import com.rahulsengupta.persistence.DatabaseMeta
import com.rahulsengupta.persistence.dao.GlobalHistoricalDao
import com.rahulsengupta.persistence.dao.GlobalTimelineDao
import com.rahulsengupta.persistence.dao.GlobalTotalsDao
import com.rahulsengupta.persistence.dao.HeadlinesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): CovidTrackerApplicationDatabase {
        return Room.databaseBuilder(
            application,
            CovidTrackerApplicationDatabase::class.java,
            DatabaseMeta.NAME
        ).fallbackToDestructiveMigration().build()
    }

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
}