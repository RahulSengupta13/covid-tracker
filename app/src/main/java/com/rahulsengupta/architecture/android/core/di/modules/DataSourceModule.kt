package com.rahulsengupta.architecture.android.core.di.modules

import com.rahulsengupta.architecture.android.core.datasource.TypiCodeDataSource
import com.rahulsengupta.network.services.NovelCovid19Service
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    fun providesTypiCodeDataSource(service: NovelCovid19Service) = TypiCodeDataSource(service)
}