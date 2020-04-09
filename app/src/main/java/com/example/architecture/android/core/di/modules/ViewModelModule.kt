package com.example.architecture.android.core.di.modules

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.architecture.android.landing.LandingViewModel
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