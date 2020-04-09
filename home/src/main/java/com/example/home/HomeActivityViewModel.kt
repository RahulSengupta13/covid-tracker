package com.example.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.di.ICoroutinesDispatcher
import com.example.network.TypiCodeService
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