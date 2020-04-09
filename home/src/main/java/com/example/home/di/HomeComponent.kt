package com.example.home.di

import com.example.core.di.CoroutinesModule
import com.example.core.di.ViewModelFactoryModule
import com.example.home.HomeActivity
import com.example.home.di.modules.HomeModules
import com.example.network.di.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        HomeModules::class,
        NetworkModule::class,
        CoroutinesModule::class,
        ViewModelFactoryModule::class
    ]
)
interface HomeComponent {

    fun inject(activity: HomeActivity)
}