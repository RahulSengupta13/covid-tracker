package com.rahulsengupta.architecture.android.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulsengupta.core.di.ICoroutinesDispatcher
import com.rahulsengupta.persistence.dao.GlobalTotalsDao
import com.rahulsengupta.persistence.enitity.GlobalTotalsEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class LandingViewModel @Inject constructor(
    private val dispatcher: ICoroutinesDispatcher,
    private val repository: ILandingRepository,
    private val globalTotalsDao: GlobalTotalsDao
) : ViewModel() {

    fun initialize() {
        viewModelScope.launch(dispatcher.IO) {
            val globalTotals = repository.getGlobalTotals().data ?: return@launch
            val globalTotalsEntity = GlobalTotalsEntity(
                globalTotals.active,
                globalTotals.affectedCountries,
                globalTotals.cases,
                globalTotals.casesPerOneMillion,
                globalTotals.critical,
                globalTotals.deaths,
                globalTotals.deathsPerOneMillion,
                globalTotals.recovered,
                globalTotals.tests,
                globalTotals.testsPerOneMillion,
                globalTotals.todayCases,
                globalTotals.todayDeaths,
                globalTotals.updated
            )
            globalTotalsDao.insertOrReplace(item = globalTotalsEntity)
        }
    }
}