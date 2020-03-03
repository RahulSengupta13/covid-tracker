package com.example.architecture.android.core.di

import android.app.Application
import com.example.architecture.android.DaggerSampleApplication
import com.example.architecture.android.core.di.modules.AppModule
import com.example.architecture.android.core.di.modules.CoroutinesModule
import com.example.network.di.NetworkModule
import com.example.network.di.WebSocketModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        CoroutinesModule::class,
        NetworkModule::class,
        WebSocketModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: DaggerSampleApplication)
}