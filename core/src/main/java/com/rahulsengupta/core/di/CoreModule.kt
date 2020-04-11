package com.rahulsengupta.core.di

import com.rahulsengupta.core.repository.CoreRepository
import com.rahulsengupta.core.repository.ICoreRepository
import com.rahulsengupta.core.usecase.GetGlobalHistoricalUseCase
import com.rahulsengupta.core.usecase.GetGlobalTimelineUseCase
import com.rahulsengupta.core.usecase.IGetGlobalHistoricalUseCase
import com.rahulsengupta.core.usecase.IGetGlobalTimelineUseCase
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
}