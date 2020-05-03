package com.rahulsengupta.core.di

import com.rahulsengupta.core.repository.CoreRepository
import com.rahulsengupta.core.repository.ICoreRepository
import com.rahulsengupta.core.usecase.*
import com.rahulsengupta.network.di.NetworkUseCaseModule
import com.rahulsengupta.persistence.di.PersistenceUseCaseModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkUseCaseModule::class, PersistenceUseCaseModule::class])
abstract class CoreModule {

    @Singleton
    @Binds
    abstract fun provideCoreRepository(coreRepository: CoreRepository): ICoreRepository

    @Singleton
    @Binds
    abstract fun provideLoadGlobalTimelineUseCase(useCase: LoadGlobalTimelineUseCase): ILoadGlobalTimelineUseCase

    @Singleton
    @Binds
    abstract fun provideLoadGlobalHistoricalUseCase(useCase: LoadGlobalHistoricalUseCase): ILoadGlobalHistoricalUseCase

    @Singleton
    @Binds
    abstract fun provideLoadCountriesHistoricalUseCase(useCase: LoadCountriesHistoricalUseCase): ILoadCountriesHistoricalUseCase

    @Singleton
    @Binds
    abstract fun provideLoadGlobalCountryUseCase(useCase: LoadGlobalCountryUseCase): ILoadGlobalCountryUseCase

    @Singleton
    @Binds
    abstract fun provideLoadGlobalTotalsUseCase(useCase: LoadGlobalTotalsUseCase): ILoadGlobalTotalsUseCase

    @Singleton
    @Binds
    abstract fun provideLoadHeadlinesUseCase(useCase: LoadHeadlinesUseCase): ILoadHeadlinesUseCase

    @Singleton
    @Binds
    abstract fun provideGetIsDataDownloadedUseCase(useCase: GetIsDataDownloadedUseCase): IGetIsDataDownloadedUseCase

    @Singleton
    @Binds
    abstract fun provideThemeHelperUseCase(useCase: ThemeHelperUseCase): IThemeHelperUseCase
}