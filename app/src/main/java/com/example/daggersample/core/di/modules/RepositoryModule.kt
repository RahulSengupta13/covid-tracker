package com.example.daggersample.core.di.modules

import com.example.daggersample.core.network.TypiCodeDataSource
import com.example.daggersample.core.network.websocket.WebSocketService
import com.example.daggersample.core.repository.IWebSocketRepository
import com.example.daggersample.core.repository.WebSocketRepository
import com.example.daggersample.landing.ILandingRepository
import com.example.daggersample.landing.LandingRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesLandingRepository(dataSource: TypiCodeDataSource): ILandingRepository {
        return LandingRepository(dataSource)
    }

    @Provides
    @Singleton
    fun providesWebSocketRepository(service: WebSocketService): IWebSocketRepository {
        return WebSocketRepository(service)
    }
}