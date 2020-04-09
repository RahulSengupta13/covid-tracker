package com.rahulsengupta.architecture.android.core.di.modules

import com.rahulsengupta.architecture.android.core.base.InjectableActivity
import com.rahulsengupta.architecture.android.landing.LandingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeInjectableActivity(): InjectableActivity

    @ContributesAndroidInjector
    abstract fun contributeLandingActivity(): LandingActivity
}