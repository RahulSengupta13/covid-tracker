package com.example.architecture.android.core.di.modules

import com.example.architecture.android.core.InjectableActivity
import com.example.architecture.android.landing.LandingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeInjectableActivity(): InjectableActivity

    @ContributesAndroidInjector
    abstract fun contributeLandingActivity(): LandingActivity
}