package com.rahulsengupta.network.di

import com.rahulsengupta.network.datasource.NovelCovid19DataSource
import com.rahulsengupta.network.services.NovelCovid19Service
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class DataSourceModule {

    @Provides
    fun providesNovelCovid19DataSource(service: NovelCovid19Service) =
        NovelCovid19DataSource(service)
}