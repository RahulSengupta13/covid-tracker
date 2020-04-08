package com.example.architecture.android.core.di.modules

import com.example.architecture.android.core.datasource.TypiCodeDataSource
import com.example.architecture.android.landing.ILandingRepository
import com.example.architecture.android.landing.LandingRepository
import com.example.network.di.NetworkModule
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