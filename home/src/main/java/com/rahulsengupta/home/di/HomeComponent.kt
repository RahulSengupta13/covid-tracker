package com.rahulsengupta.home.di

import android.app.Application
import com.rahulsengupta.core.di.CoroutinesModule
import com.rahulsengupta.core.di.ViewModelFactoryModule
import com.rahulsengupta.home.HomeActivity
import com.rahulsengupta.home.di.modules.HomeModules
import com.rahulsengupta.network.di.NetworkModule
import com.rahulsengupta.persistence.di.PersistenceModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        HomeModules::class,
        CoroutinesModule::class,
        ViewModelFactoryModule::class
    ]
)
interface HomeComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun networkModule(networkModule: NetworkModule): Builder

        @BindsInstance
        fun persistenceModule(persistenceModule: PersistenceModule): Builder

        fun build(): HomeComponent
    }

    fun inject(activity: HomeActivity)

}