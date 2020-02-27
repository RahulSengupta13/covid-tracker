package com.example.daggersample.core.di.modules

import com.example.daggersample.core.InjectableActivity
import com.example.daggersample.landing.LandingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeInjectableActivity(): InjectableActivity

    @ContributesAndroidInjector
    abstract fun contributeLandingActivity(): LandingActivity
}