package com.rahulsengupta.architecture.android.core.di.modules

import com.rahulsengupta.architecture.android.core.datasource.NovelCovid19DataSource
import com.rahulsengupta.architecture.android.landing.ILandingRepository
import com.rahulsengupta.architecture.android.landing.LandingRepository
import com.rahulsengupta.network.di.NetworkModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun providesLandingRepository(dataSource: NovelCovid19DataSource): ILandingRepository {
        return LandingRepository(dataSource)
    }
}