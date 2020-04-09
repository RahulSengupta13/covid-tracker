package com.rahulsengupta.architecture.android.core.di.modules

import androidx.lifecycle.ViewModel
import com.rahulsengupta.architecture.android.landing.LandingViewModel
import com.rahulsengupta.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LandingViewModel::class)
    abstract fun providesLandingViewModel(viewModel: LandingViewModel): ViewModel
}