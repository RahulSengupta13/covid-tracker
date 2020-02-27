package com.example.daggersample.core.di

import android.app.Application
import com.example.daggersample.DaggerSampleApplication
import com.example.daggersample.core.di.modules.AppModule
import com.example.daggersample.core.di.modules.CoroutinesModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        CoroutinesModule::class
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