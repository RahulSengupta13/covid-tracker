package com.example.architecture.android.core.di.modules

import com.example.architecture.android.core.base.InjectableFragment
import com.example.architecture.android.landing.LandingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeInjectableFragment(): InjectableFragment

    @ContributesAndroidInjector
    abstract fun contributeLandingFragment(): LandingFragment
}