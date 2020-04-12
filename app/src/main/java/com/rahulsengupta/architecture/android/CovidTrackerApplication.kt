package com.rahulsengupta.architecture.android

import android.app.Application
import com.rahulsengupta.architecture.android.core.di.AppInjector
import com.rahulsengupta.home.di.DaggerHomeComponent
import com.rahulsengupta.home.di.HomeComponent
import com.rahulsengupta.home.di.provider.HomeComponentProvider
import com.rahulsengupta.network.di.DataSourceModule
import com.rahulsengupta.network.di.NetworkModule
import com.rahulsengupta.persistence.di.PersistenceModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class CovidTrackerApplication : Application(), HasAndroidInjector, HomeComponentProvider {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private val dataSourceModule by lazy { DataSourceModule() }
    private val persistenceModule by lazy { PersistenceModule() }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        AppInjector.init(this, dataSourceModule, persistenceModule)
    }

    override fun androidInjector() = dispatchingAndroidInjector

    override fun getHomeComponent(): HomeComponent {
        return DaggerHomeComponent
            .builder()
            .application(this)
            .dataSourceModule(dataSourceModule)
            .persistenceModule(persistenceModule)
            .build()
    }
}