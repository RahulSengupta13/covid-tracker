package com.rahulsengupta.home.di.modules

import com.rahulsengupta.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeActivityModules {

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity
}