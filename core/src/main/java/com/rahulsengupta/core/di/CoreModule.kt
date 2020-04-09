package com.rahulsengupta.core.di

import com.rahulsengupta.core.repository.CoreRepository
import com.rahulsengupta.core.repository.ICoreRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Singleton
    @Provides
    fun provideCoreRepository(coreRepository: CoreRepository): ICoreRepository = coreRepository
}