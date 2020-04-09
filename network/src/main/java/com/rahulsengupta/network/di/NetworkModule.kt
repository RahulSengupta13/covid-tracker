package com.rahulsengupta.network.di

import com.rahulsengupta.network.services.NovelCovid19Service
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
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
    fun provideConverterFactory(mediaType: MediaType): Converter.Factory = Json.asConverterFactory(mediaType)

    @Singleton
    @Provides
    fun providesTypiCodeService(
        okhttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ) = provideService(okhttpClient, converterFactory, NovelCovid19Service::class.java)

    private fun <T> provideService(
        okhttpClient: OkHttpClient,
        converterFactory: Converter.Factory, clazz: Class<T>
    ): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }


    private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NovelCovid19Service.BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }
}