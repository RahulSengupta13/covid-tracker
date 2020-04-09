package com.example.home.di.modules

import dagger.Module

@Module(
    includes = [
        HomeActivityModules::class,
        HomeViewModelModule::class
    ]
)
class HomeModules {

}