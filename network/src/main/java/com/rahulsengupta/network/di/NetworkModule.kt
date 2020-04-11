package com.rahulsengupta.network.di

import com.rahulsengupta.network.services.NovelCovid19Service
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rahulsengupta.network.services.AboutCoronaService
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor)
            .build()

    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideMediaType() ="application/json".toMediaType()

    @Provides
    @Singleton
    fun provideConverterFactory(mediaType: MediaType): Converter.Factory = Json(JsonConfiguration(ignoreUnknownKeys = true)).asConverterFactory(mediaType)

    @Singleton
    @Provides
    fun provideNovelCovid19Service(
        okhttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ) = provideService(NovelCovid19Service.BASE_URL, okhttpClient, converterFactory, NovelCovid19Service::class.java)

    @Singleton
    @Provides
    fun provideAboutCoronaService(
        okhttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ) = provideService(AboutCoronaService.BASE_URL, okhttpClient, converterFactory, AboutCoronaService::class.java)

    private fun <T> provideService(
        baseUrl: String,
        okhttpClient: OkHttpClient,
        converterFactory: Converter.Factory, clazz: Class<T>
    ): T {
        return createRetrofit(baseUrl, okhttpClient, converterFactory).create(clazz)
    }


    private fun createRetrofit(
        baseUrl: String,
        okhttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }
}