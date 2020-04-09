package com.rahulsengupta.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulsengupta.core.di.ICoroutinesDispatcher
import com.rahulsengupta.network.services.TypiCodeService
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor(
    private val service: TypiCodeService,
    private val dispatcher: ICoroutinesDispatcher
): ViewModel() {

    val uiData = MutableLiveData<Int>()

    init {
        viewModelScope.launch(dispatcher.IO) {
            uiData.postValue(service.getPosts().body()?.size ?: 0)
        }
    }

}