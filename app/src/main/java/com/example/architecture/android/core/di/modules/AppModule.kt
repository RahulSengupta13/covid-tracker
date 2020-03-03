package com.example.architecture.android.core.di.modules

import com.example.architecture.android.core.datasource.TypiCodeDataSource
import com.example.network.TypiCodeService
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        ViewModelModule::class,
        RepositoryModule::class,
        ActivityModule::class,
        FragmentModule::class
    ]
)
class AppModule {

    @Provides
    fun providesTypiCodeDataSource(service: TypiCodeService) = TypiCodeDataSource(service)
}