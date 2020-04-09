package com.example.architecture.android

import android.app.Application
import com.example.architecture.android.core.di.AppInjector
import com.example.network.di.NetworkModule
import com.example.home.di.DaggerHomeComponent
import com.example.home.di.HomeComponent
import com.example.home.di.provider.HomeComponentProvider
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class DaggerSampleApplication : Application(), HasAndroidInjector, HomeComponentProvider {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private val networkModule by lazy { NetworkModule() }

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this, networkModule)
    }

    override fun androidInjector() = dispatchingAndroidInjector

    override fun getHomeComponent(): HomeComponent {
        return DaggerHomeComponent.builder().networkModule(networkModule).build()
    }
}