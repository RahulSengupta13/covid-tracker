package com.rahulsengupta.home.di.modules

import androidx.lifecycle.ViewModel
import com.rahulsengupta.core.di.ViewModelKey
import com.rahulsengupta.home.HomeActivityViewModel
import com.rahulsengupta.network.di.NetworkModule
import com.rahulsengupta.persistence.di.PersistenceModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [NetworkModule::class, PersistenceModule::class])
abstract class HomeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeActivityViewModel::class)
    abstract fun providesHomeActivityViewModel(viewModel: HomeActivityViewModel): ViewModel
}