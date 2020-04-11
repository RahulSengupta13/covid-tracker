package com.rahulsengupta.architecture.android.flows.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulsengupta.architecture.android.flows.splash.model.ViewEffect
import com.rahulsengupta.architecture.android.flows.splash.model.ViewEffect.NavigateToDashboard
import com.rahulsengupta.core.di.ICoroutinesDispatcher
import com.rahulsengupta.core.repository.ICoreRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    dispatcher: ICoroutinesDispatcher,
    private val coreRepository: ICoreRepository
) : ViewModel() {

    private val _viewEffect = MutableLiveData<ViewEffect>()
    val viewEffect: LiveData<ViewEffect>
        get() = _viewEffect

    init {
        viewModelScope.launch(dispatcher.IO) {
            initialize()
        }
    }

    private suspend fun initialize() {
        coreRepository.initializeAsync().await()
        _viewEffect.postValue(NavigateToDashboard)
    }
}