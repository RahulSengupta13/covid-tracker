package com.rahulsengupta.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulsengupta.core.di.ICoroutinesDispatcher
import com.rahulsengupta.network.services.NovelCovid19Service
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor(
    private val service: NovelCovid19Service,
    private val dispatcher: ICoroutinesDispatcher
) : ViewModel() {

    fun load() {
        viewModelScope.launch(dispatcher.IO) {
            val response = service.getGlobalTotals().body()
            Timber.d(response?.toString())
        }
    }
}