package com.rahulsengupta.architecture.android.core.di.modules

import com.rahulsengupta.network.di.NetworkModule
import dagger.Module

@Module(
    includes = [
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        NetworkModule::class
    ]
)
class AppModule {

}