package com.rahulsengupta.architecture.android.core.di.modules

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@AssistedModule
@Module(includes = [AssistedInject_AppAssistedInjectionModule::class])
interface AppAssistedInjectionModule