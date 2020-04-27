package com.rahulsengupta.architecture.android.core.di.modules

import androidx.lifecycle.ViewModel
import com.rahulsengupta.architecture.android.flows.dashboard.DashboardViewModel
import com.rahulsengupta.architecture.android.flows.search.SearchViewModel
import com.rahulsengupta.architecture.android.flows.splash.SplashViewModel
import com.rahulsengupta.core.di.ViewModelKey
import com.rahulsengupta.network.di.DataSourceModule
import com.rahulsengupta.persistence.di.PersistenceModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [PersistenceModule::class])
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun providesLandingViewModel(viewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun providesSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun providesSearchViewModel(viewModel: SearchViewModel): ViewModel
}