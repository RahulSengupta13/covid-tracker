package com.example.home.di.modules

import com.example.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeActivityModules {

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity
}