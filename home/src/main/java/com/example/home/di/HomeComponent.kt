package com.example.home.di

import com.example.core.di.CoroutinesModule
import com.example.network.di.NetworkModule
import com.example.home.HomeActivity
import com.example.home.di.modules.HomeModules
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        HomeModules::class,
        NetworkModule::class,
        CoroutinesModule::class
    ]
)
interface HomeComponent {

    fun inject(activity: HomeActivity)
}