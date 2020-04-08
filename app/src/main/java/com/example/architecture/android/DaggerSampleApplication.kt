package com.example.architecture.android

import android.app.Application
import com.example.architecture.android.core.di.AppInjector
import com.example.network.di.NetworkModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class DaggerSampleApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private val networkModule by lazy { NetworkModule() }

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this, networkModule)
    }

    override fun androidInjector() = dispatchingAndroidInjector
}