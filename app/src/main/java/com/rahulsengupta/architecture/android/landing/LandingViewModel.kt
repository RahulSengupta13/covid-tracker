package com.rahulsengupta.architecture.android.landing

import androidx.lifecycle.ViewModel
import com.rahulsengupta.core.di.ICoroutinesDispatcher
import javax.inject.Inject

class LandingViewModel @Inject constructor(
    dispatcher: ICoroutinesDispatcher,
    private val repository: ILandingRepository
) : ViewModel() {

}