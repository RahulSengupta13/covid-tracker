package com.rahulsengupta.architecture.android.core.di.modules

import com.rahulsengupta.architecture.android.core.datasource.TypiCodeDataSource
import com.rahulsengupta.network.TypiCodeService
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    fun providesTypiCodeDataSource(service: TypiCodeService) = TypiCodeDataSource(service)
}