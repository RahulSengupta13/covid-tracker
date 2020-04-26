package com.rahulsengupta.network.di

import com.rahulsengupta.network.usecase.*
import dagger.Binds
import dagger.Module

@Module(includes = [DataSourceModule::class])
abstract class NetworkUseCaseModule {

    @Binds
    abstract fun bindFetchGlobalTimelineUseCase(useCase: FetchGlobalTimelineUseCase): IFetchGlobalTimelineUseCase

    @Binds
    abstract fun bindFetchGlobalHistoricalUseCase(useCase: FetchGlobalHistoricalUseCase): IFetchGlobalHistoricalUseCase

    @Binds
    abstract fun bindFetchCountriesHistoricalUseCase(useCase: FetchCountriesHistoricalUseCase): IFetchCountriesHistoricalUseCase

    @Binds
    abstract fun bindFetchGlobalCountryUseCase(useCase: FetchGlobalCountryUseCase): IFetchGlobalCountryUseCase

    @Binds
    abstract fun bindFetchGlobalTotalsUseCase(useCase: FetchGlobalTotalsUseCase): IFetchGlobalTotalsUseCase

    @Binds
    abstract fun bindFetchHeadlinesUseCase(useCase: FetchHeadlinesUseCase): IFetchHeadlinesUseCase
}