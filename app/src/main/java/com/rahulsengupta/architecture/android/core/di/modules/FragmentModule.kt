package com.rahulsengupta.architecture.android.core.di.modules

import com.rahulsengupta.core.base.InjectableFragment
import com.rahulsengupta.architecture.android.flows.dashboard.DashboardFragment
import com.rahulsengupta.architecture.android.flows.livereports.LiveReportsFragment
import com.rahulsengupta.architecture.android.flows.search.SearchFragment
import com.rahulsengupta.architecture.android.flows.settings.SettingsFragment
import com.rahulsengupta.architecture.android.flows.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeInjectableFragment(): InjectableFragment

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeLandingFragment(): DashboardFragment

    @ContributesAndroidInjector
    abstract fun contributeLiveReportsFragment(): LiveReportsFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingsFragment(): SettingsFragment
}