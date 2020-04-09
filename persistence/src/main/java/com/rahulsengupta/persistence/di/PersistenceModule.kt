package com.rahulsengupta.persistence.di

import android.app.Application
import androidx.room.Room
import com.rahulsengupta.persistence.CovidTrackerApplicationDatabase
import com.rahulsengupta.persistence.DatabaseMeta
import com.rahulsengupta.persistence.dao.GlobalTotalsDao
import dagger.Module
import dagger.Provides
import javax.inject.Inject
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
        ).build()
    }

    @Provides
    @Singleton
    fun provideGlobalTotalsDao(database: CovidTrackerApplicationDatabase): GlobalTotalsDao {
        return database.globalTotalDao()
    }
}