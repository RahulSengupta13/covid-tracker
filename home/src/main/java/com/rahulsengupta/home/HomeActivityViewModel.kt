package com.rahulsengupta.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulsengupta.core.di.ICoroutinesDispatcher
import com.rahulsengupta.network.services.NovelCovid19Service
import com.rahulsengupta.persistence.dao.GlobalTotalsDao
import com.rahulsengupta.persistence.enitity.GlobalTotalsEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor(
    dispatcher: ICoroutinesDispatcher,
    private val globalTotalsDao: GlobalTotalsDao
) : ViewModel() {

    val uiData = MutableLiveData<List<GlobalTotalsEntity>>()

    init {
        viewModelScope.launch(dispatcher.IO) {
            globalTotalsDao.getGlobalTotals().collect {
                uiData.postValue(it)
            }
        }
    }
}