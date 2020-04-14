package com.rahulsengupta.core.di

import com.rahulsengupta.core.repository.CoreRepository
import com.rahulsengupta.core.repository.ICoreRepository
import com.rahulsengupta.core.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Singleton
    @Provides
    fun provideCoreRepository(coreRepository: CoreRepository): ICoreRepository = coreRepository

    @Singleton
    @Provides
    fun provideGlobalHistoricalUseCase(useCase: GetGlobalHistoricalUseCase): IGetGlobalHistoricalUseCase = useCase

    @Singleton
    @Provides
    fun provideGlobalTimelineUseCase(useCase: GetGlobalTimelineUseCase): IGetGlobalTimelineUseCase = useCase

    @Singleton
    @Provides
    fun provideGetNewsHeadlinesUseCase(useCase: GetNewsHeadlinesUseCase): IGetNewsHeadlinesUseCase = useCase

    @Singleton
    @Provides
    fun provideGetGlobalCountryUseCase(useCase: GetGlobalCountryUseCase): IGetGlobalCountryUseCase = useCase
}