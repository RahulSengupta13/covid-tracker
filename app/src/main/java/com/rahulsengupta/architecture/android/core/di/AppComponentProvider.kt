package com.rahulsengupta.architecture.android.core.di

import androidx.fragment.app.Fragment

interface AppComponentProvider {
    val component: AppComponent
}

val Fragment.injector get() = (requireActivity().application as AppComponentProvider).component