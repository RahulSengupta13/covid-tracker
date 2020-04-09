package com.rahulsengupta.architecture.android.core.di.modules

import com.rahulsengupta.architecture.android.core.datasource.NovelCovid19DataSource
import com.rahulsengupta.network.services.NovelCovid19Service
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    fun providesNovelCovid19DataSource(service: NovelCovid19Service) = NovelCovid19DataSource(service)
}