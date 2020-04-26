package com.rahulsengupta.persistence.di

import com.rahulsengupta.persistence.usecase.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class PersistenceUseCaseModule {

    @Singleton
    @Binds
    abstract fun provideGlobalHistoricalUseCase(useCase: GetGlobalHistoricalUseCase): IGetGlobalHistoricalUseCase

    @Singleton
    @Binds
    abstract fun provideGlobalTimelineUseCase(useCase: GetGlobalTimelineUseCase): IGetGlobalTimelineUseCase

    @Singleton
    @Binds
    abstract fun provideGetNewsHeadlinesUseCase(useCase: GetNewsHeadlinesUseCase): IGetNewsHeadlinesUseCase

    @Singleton
    @Binds
    abstract fun provideGetGlobalCountryUseCase(useCase: GetGlobalCountryUseCase): IGetGlobalCountryUseCase

    @Singleton
    @Binds
    abstract fun provideGetCountryHistoricalUseCase(useCase: GetCountryHistoricalUseCase): IGetCountryHistoricalUseCase
}