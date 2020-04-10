package com.rahulsengupta.architecture.android.flows.dashboard

import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulsengupta.architecture.R
import com.rahulsengupta.architecture.android.flows.dashboard.model.DashBoardChartState.*
import com.rahulsengupta.architecture.android.flows.dashboard.model.DashboardState
import com.rahulsengupta.architecture.android.flows.dashboard.model.ViewState
import com.rahulsengupta.architecture.android.flows.dashboard.model.ViewState.ChartData
import com.rahulsengupta.core.di.ICoroutinesDispatcher
import com.rahulsengupta.core.extensions.getFormattedDate
import com.rahulsengupta.core.repository.ICoreRepository
import com.rahulsengupta.core.usecase.IGetGlobalHistoricalUseCase
import com.rahulsengupta.persistence.dao.GlobalTotalsDao
import com.rahulsengupta.persistence.enitity.GlobalHistoricalEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val dispatcher: ICoroutinesDispatcher,
    private val globalTotalsDao: GlobalTotalsDao,
    private val coreRepository: ICoreRepository,
    private val globalHistoricalUseCase: IGetGlobalHistoricalUseCase
) : ViewModel() {

    private var state = DashboardState()

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    val buttonGroupStartId = ObservableInt(state.chartState.buttonId)
    val totalTitleId = ObservableInt(state.chartState.titleId)
    val chartAccentColor = ObservableInt(state.chartState.chartAccentId)
    private var globalHistoricalEntity: GlobalHistoricalEntity? = null

    init {
        initialize()
    }

    private fun initialize() {
        viewModelScope.launch(dispatcher.IO) {
            coreRepository.initialize()
            globalHistoricalUseCase.flow.collect {
                globalHistoricalEntity = it
                processGlobalHistoricalEntity(it)
            }
        }
    }

    private fun processGlobalHistoricalEntity(entity: GlobalHistoricalEntity) {
        viewModelScope.launch {
            val chartList = when (state.chartState) {
                CASES -> {
                    entity.cases.toList()
                }
                DEATHS -> {
                    entity.deaths.toList()
                }
                RECOVERED -> {
                    entity.recovered.toList()
                }
            }
            val chartData = ChartData(
                chartList.map {
                    ChartData.ChartDataValue(
                        it.first.getFormattedDate(),
                        it.second
                    )
                },
                state.chartState.chartAccentId
            )
            _viewState.postValue(chartData)
        }
    }

    fun onChartButtonClicked(buttonId: Int) {
        when (buttonId) {
            R.id.btnCases -> {
                if (state.chartState != CASES) {
                    state = state.copy(chartState = CASES)
                    globalHistoricalEntity?.let { processGlobalHistoricalEntity(it) }
                }
            }
            R.id.btnRecovered -> {
                if (state.chartState != RECOVERED) {
                    state = state.copy(chartState = RECOVERED)
                    globalHistoricalEntity?.let { processGlobalHistoricalEntity(it) }
                }
            }
            R.id.btnDeaths -> {
                if (state.chartState != DEATHS) {
                    state = state.copy(chartState = DEATHS)
                    globalHistoricalEntity?.let { processGlobalHistoricalEntity(it) }
                }
            }
        }
        totalTitleId.set(state.chartState.titleId)
        chartAccentColor.set(state.chartState.chartAccentId)
    }
}