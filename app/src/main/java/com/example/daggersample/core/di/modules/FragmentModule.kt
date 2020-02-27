package com.example.daggersample.core.di.modules

import com.example.daggersample.core.InjectableFragment
import com.example.daggersample.landing.LandingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeInjectableFragment(): InjectableFragment

    @ContributesAndroidInjector
    abstract fun contributeLandingFragment(): LandingFragment
}