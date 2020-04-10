package com.rahulsengupta.architecture.android.flows.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulsengupta.core.di.ICoroutinesDispatcher
import com.rahulsengupta.core.repository.ICoreRepository
import com.rahulsengupta.persistence.dao.GlobalTotalsDao
import com.rahulsengupta.persistence.enitity.GlobalTotalsEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val dispatcher: ICoroutinesDispatcher,
    private val globalTotalsDao: GlobalTotalsDao,
    private val coreRepository: ICoreRepository
) : ViewModel() {

    init {
        initialize()
    }

    val uiData = MutableLiveData<List<GlobalTotalsEntity>>()

    private fun initialize() {
        viewModelScope.launch(dispatcher.IO) {
            coreRepository.initialize()
            globalTotalsDao.getGlobalTotals().collect {
                uiData.postValue(it)
            }
        }
    }
}