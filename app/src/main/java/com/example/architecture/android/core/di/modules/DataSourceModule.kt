package com.example.architecture.android.core.di.modules

import com.example.architecture.android.core.datasource.TypiCodeDataSource
import com.example.network.TypiCodeService
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    fun providesTypiCodeDataSource(service: TypiCodeService) = TypiCodeDataSource(service)
}