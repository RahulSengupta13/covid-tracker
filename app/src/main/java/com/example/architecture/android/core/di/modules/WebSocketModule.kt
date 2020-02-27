package com.example.architecture.android.core.di.modules

import android.app.Application
import com.example.architecture.android.core.network.websocket.WebSocketService
import com.tinder.scarlet.Lifecycle
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.retry.ExponentialWithJitterBackoffStrategy
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class WebSocketModule {

    @Provides
    @Named("WebSocketOkHttpClient")
    fun provideWebSocketOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    @Provides
    fun providesBackOffStrategy() = ExponentialWithJitterBackoffStrategy(5000, 5000)

    @Provides
    fun providesWebSocketLifeCycle(application: Application) =
        AndroidLifecycle.ofApplicationForeground(application)

    @Provides
    fun providesScarlet(
        lifecycle: Lifecycle,
        @Named("WebSocketOkHttpClient") okHttpClient: OkHttpClient,
        backOffStrategy: ExponentialWithJitterBackoffStrategy
    ) = Scarlet.Builder()
        .webSocketFactory(okHttpClient.newWebSocketFactory(WebSocketService.BASE_URL))
        .addMessageAdapterFactory(GsonMessageAdapter.Factory())
        .addStreamAdapterFactory(CoroutinesStreamAdapterFactory())
        .backoffStrategy(backOffStrategy)
        .lifecycle(lifecycle)
        .build()
}