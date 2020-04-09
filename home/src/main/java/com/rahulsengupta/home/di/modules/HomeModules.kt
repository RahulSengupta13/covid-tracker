package com.rahulsengupta.home.di.modules

import dagger.Module

@Module(
    includes = [
        HomeActivityModules::class,
        HomeViewModelModule::class
    ]
)
class HomeModules {

}