package com.rahulsengupta.architecture.android.core.di

import android.app.Application
import com.rahulsengupta.architecture.android.CovidTrackerApplication
import com.rahulsengupta.architecture.android.core.di.modules.AppModule
import com.rahulsengupta.core.di.CoroutinesModule
import com.rahulsengupta.core.di.ViewModelFactoryModule
import com.rahulsengupta.network.di.NetworkModule
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
        ViewModelFactoryModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }

    fun inject(application: CovidTrackerApplication)
}