package com.rahulsengupta.architecture.android.core.di.modules

import com.rahulsengupta.core.base.InjectableFragment
import com.rahulsengupta.architecture.android.landing.LandingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeInjectableFragment(): InjectableFragment

    @ContributesAndroidInjector
    abstract fun contributeLandingFragment(): LandingFragment
}