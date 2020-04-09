package com.rahulsengupta.home.di

import com.rahulsengupta.core.di.CoroutinesModule
import com.rahulsengupta.core.di.ViewModelFactoryModule
import com.rahulsengupta.home.HomeActivity
import com.rahulsengupta.home.di.modules.HomeModules
import com.rahulsengupta.network.di.NetworkModule
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