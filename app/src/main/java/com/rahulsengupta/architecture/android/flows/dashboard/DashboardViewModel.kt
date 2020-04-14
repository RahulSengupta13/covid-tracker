package com.rahulsengupta.architecture.android.flows.dashboard

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulsengupta.architecture.R
import com.rahulsengupta.architecture.android.flows.dashboard.model.DashBoardChartModeState.DAILY
import com.rahulsengupta.architecture.android.flows.dashboard.model.DashBoardChartModeState.TOTAL
import com.rahulsengupta.architecture.android.flows.dashboard.model.DashBoardChartState.*
import com.rahulsengupta.architecture.android.flows.dashboard.model.DashboardState
import com.rahulsengupta.architecture.android.flows.dashboard.model.NewsItem
import com.rahulsengupta.architecture.android.flows.dashboard.model.ViewState
import com.rahulsengupta.architecture.android.flows.dashboard.model.ViewState.ChartData
import com.rahulsengupta.core.di.ICoroutinesDispatcher
import com.rahulsengupta.core.extensions.getFormattedDateFromUTCTimestamp
import com.rahulsengupta.core.extensions.getShortFormattedDateFromUTCTimestamp
import com.rahulsengupta.core.usecase.IGetGlobalCountryUseCase
import com.rahulsengupta.core.usecase.IGetGlobalHistoricalUseCase
import com.rahulsengupta.core.usecase.IGetGlobalTimelineUseCase
import com.rahulsengupta.core.usecase.IGetNewsHeadlinesUseCase
import com.rahulsengupta.persistence.enitity.ArticleEntity
import com.rahulsengupta.persistence.enitity.GlobalHistoricalEntity
import com.rahulsengupta.persistence.enitity.GlobalTimelineEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val dispatcher: ICoroutinesDispatcher,
    private val globalHistoricalUseCase: IGetGlobalHistoricalUseCase,
    private val globalTimelineUseCase: IGetGlobalTimelineUseCase,
    private val newsHeadlinesUseCase: IGetNewsHeadlinesUseCase,
    private val globalCountryUseCase: IGetGlobalCountryUseCase
) : ViewModel() {

    private var state = DashboardState()

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    val buttonGroupStartId = ObservableInt(state.chartState.typeButtonId)
    val modeButtonGroupStartId = ObservableInt(state.chartModeState.modeButtonId)
    val totalTitleId = ObservableInt(state.chartState.titleIdTotal)
    val chartAccentColor = ObservableInt(state.chartState.chartAccentId)
    val newsItems = ObservableField<List<NewsItem>>(emptyList())

    private var globalHistoricalEntity: GlobalHistoricalEntity? = null
    private var globalTimelineEntity: GlobalTimelineEntity? = null

    init {
        initialize()
    }

    private fun initialize() {

        viewModelScope.launch(dispatcher.IO) {
            globalHistoricalUseCase.flow.collect {
                globalHistoricalEntity = it
            }
        }

        viewModelScope.launch(dispatcher.IO) {
            globalTimelineUseCase.flow.collect {
                globalTimelineEntity = it
            }
        }

        viewModelScope.launch(dispatcher.IO) {
            globalHistoricalUseCase.flow.combine(globalTimelineUseCase.flow) { history, timeline ->
                Pair(history, timeline)
            }.collect {
                if (state.chartModeState == DAILY) {
                    totalTitleId.set(state.chartState.titleIdDaily)
                    processGlobalTimelineEntity(it?.second)
                } else {
                    totalTitleId.set(state.chartState.titleIdTotal)
                    processGlobalHistoricalEntity(it?.first)
                }
            }
        }

        viewModelScope.launch(dispatcher.IO) {
            newsHeadlinesUseCase.flow.collect {
                processArticles(it)
            }
        }

        viewModelScope.launch(dispatcher.IO) {
            globalCountryUseCase.flow.collect {
                Timber.d(it.toString())
            }
        }
    }

    private fun processArticles(articles: List<ArticleEntity>?) {
        val items = mutableListOf<NewsItem>()
        val articleList = articles?.map {
            NewsItem.Headline(
                title = it.title,
                publishedAt = "published at: ${it.publishedAt.getShortFormattedDateFromUTCTimestamp()}",
                imageUrl = it.urlToImage ?: "",
                url = it.url
            )
        }?.take(20) ?: emptyList()
        items.addAll(articleList)
        items.add(NewsItem.More())
        newsItems.set(items)
    }

    private fun processGlobalHistoricalEntity(entity: GlobalHistoricalEntity?) {
        viewModelScope.launch {
            entity ?: return@launch
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
                        it.first,
                        it.second
                    )
                },
                state.chartState.chartAccentId
            )
            _viewState.postValue(chartData)
        }
    }

    private fun processGlobalTimelineEntity(entity: GlobalTimelineEntity?) {
        viewModelScope.launch(dispatcher.IO) {
            entity ?: return@launch
            val chartList = when (state.chartState) {
                CASES -> {
                    entity.list.map { ChartData.ChartDataValue(it.updatedAt, it.newConfirmed) }
                }
                DEATHS -> {
                    entity.list.map { ChartData.ChartDataValue(it.updatedAt, it.newDeaths) }
                }
                RECOVERED -> {
                    entity.list.map { ChartData.ChartDataValue(it.updatedAt, it.newRecovered) }
                }
            }
            val chartData = ChartData(
                chartList,
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
                    if (state.chartModeState == TOTAL) {
                        globalHistoricalEntity?.let { processGlobalHistoricalEntity(it) }
                    } else {
                        globalTimelineEntity?.let { processGlobalTimelineEntity(it) }
                    }
                }
            }
            R.id.btnRecovered -> {
                if (state.chartState != RECOVERED) {
                    state = state.copy(chartState = RECOVERED)
                    if (state.chartModeState == TOTAL) {
                        globalHistoricalEntity?.let { processGlobalHistoricalEntity(it) }
                    } else {
                        globalTimelineEntity?.let { processGlobalTimelineEntity(it) }
                    }
                }
            }
            R.id.btnDeaths -> {
                if (state.chartState != DEATHS) {
                    state = state.copy(chartState = DEATHS)
                    if (state.chartModeState == TOTAL) {
                        globalHistoricalEntity?.let { processGlobalHistoricalEntity(it) }
                    } else {
                        globalTimelineEntity?.let { processGlobalTimelineEntity(it) }
                    }
                }
            }
        }
        if (state.chartModeState == DAILY) {
            totalTitleId.set(state.chartState.titleIdDaily)
        } else {
            totalTitleId.set(state.chartState.titleIdTotal)
        }
        chartAccentColor.set(state.chartState.chartAccentId)
    }

    fun onChartModeButtonClicked(buttonId: Int) {
        when (buttonId) {
            R.id.btnDaily -> {
                if (state.chartModeState != DAILY) {
                    state = state.copy(chartModeState = DAILY)
                    totalTitleId.set(state.chartState.titleIdDaily)
                    globalTimelineEntity?.let { processGlobalTimelineEntity(it) }
                }
            }
            R.id.btnTotal -> {
                if (state.chartModeState != TOTAL) {
                    state = state.copy(chartModeState = TOTAL)
                    totalTitleId.set(state.chartState.titleIdTotal)
                    globalHistoricalEntity?.let { processGlobalHistoricalEntity(it) }
                }
            }
        }
    }
}