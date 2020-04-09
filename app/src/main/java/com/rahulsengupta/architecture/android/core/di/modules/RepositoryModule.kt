package com.rahulsengupta.architecture.android.core.di.modules

import com.rahulsengupta.architecture.android.core.datasource.TypiCodeDataSource
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
    fun providesLandingRepository(dataSource: TypiCodeDataSource): ILandingRepository {
        return LandingRepository(dataSource)
    }
}