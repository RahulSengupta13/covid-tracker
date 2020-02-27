package com.example.architecture.android.core.di.modules

import com.example.architecture.android.core.network.TypiCodeDataSource
import com.example.architecture.android.core.network.TypiCodeService
import com.example.architecture.android.core.network.websocket.WebSocketService
import com.tinder.scarlet.Scarlet
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(
    includes = [
        ViewModelModule::class,
        CoreDataModule::class,
        RepositoryModule::class,
        ActivityModule::class,
        FragmentModule::class,
        WebSocketModule::class
    ]
)
class AppModule {

    @Singleton
    @Provides
    fun providesTypiCodeService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, TypiCodeService::class.java)

    @Provides
    fun providesTypiCodeDataSource(service: TypiCodeService) = TypiCodeDataSource(service)

    @Provides
    fun providesWebSocketService(scarlet: Scarlet) = scarlet.create(WebSocketService::class.java)

    private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(TypiCodeService.BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory, clazz: Class<T>
    ): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }
}