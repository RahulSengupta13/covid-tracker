package com.rahulsengupta.architecture.android

import android.app.Application
import com.rahulsengupta.architecture.android.core.di.AppInjector
import com.rahulsengupta.home.di.DaggerHomeComponent
import com.rahulsengupta.home.di.HomeComponent
import com.rahulsengupta.home.di.provider.HomeComponentProvider
import com.rahulsengupta.network.di.NetworkModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class CovidTrackerApplication : Application(), HasAndroidInjector, HomeComponentProvider {

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