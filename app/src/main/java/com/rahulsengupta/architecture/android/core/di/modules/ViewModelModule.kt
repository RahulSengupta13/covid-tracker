package com.rahulsengupta.architecture.android.core.di.modules

import androidx.lifecycle.ViewModel
import com.rahulsengupta.architecture.android.landing.LandingViewModel
import com.rahulsengupta.core.di.ViewModelKey
import com.rahulsengupta.network.di.NetworkModule
import com.rahulsengupta.persistence.di.PersistenceModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [PersistenceModule::class])
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LandingViewModel::class)
    abstract fun providesLandingViewModel(viewModel: LandingViewModel): ViewModel
}