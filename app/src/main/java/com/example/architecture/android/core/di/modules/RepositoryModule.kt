package com.example.architecture.android.core.di.modules

import com.example.architecture.android.core.network.TypiCodeDataSource
import com.example.architecture.android.core.network.websocket.WebSocketService
import com.example.architecture.android.core.repository.IWebSocketRepository
import com.example.architecture.android.core.repository.WebSocketRepository
import com.example.architecture.android.landing.ILandingRepository
import com.example.architecture.android.landing.LandingRepository
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