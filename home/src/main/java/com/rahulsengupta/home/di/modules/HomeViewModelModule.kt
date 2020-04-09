package com.rahulsengupta.home.di.modules

import androidx.lifecycle.ViewModel
import com.rahulsengupta.core.di.ViewModelKey
import com.rahulsengupta.home.HomeActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeActivityViewModel::class)
    abstract fun providesHomeActivityViewModel(viewModel: HomeActivityViewModel): ViewModel
}