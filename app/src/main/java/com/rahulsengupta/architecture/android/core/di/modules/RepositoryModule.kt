package com.rahulsengupta.architecture.android.core.di.modules

import com.rahulsengupta.architecture.android.core.datasource.NovelCovid19DataSource
import com.rahulsengupta.architecture.android.landing.ILandingRepository
import com.rahulsengupta.architecture.android.landing.LandingRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesLandingRepository(dataSource: NovelCovid19DataSource): ILandingRepository {
        return LandingRepository(dataSource)
    }
}