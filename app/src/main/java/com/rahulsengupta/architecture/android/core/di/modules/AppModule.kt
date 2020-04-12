package com.rahulsengupta.architecture.android.core.di.modules

import dagger.Module

@Module(
    includes = [
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class
    ]
)
class AppModule {

}