package com.rahulsengupta.core.usecase

import com.rahulsengupta.core.di.ICoroutinesDispatcher
import com.rahulsengupta.core.model.CountryItem
import com.rahulsengupta.core.model.CountryItem.CaseType
import com.rahulsengupta.core.model.CountryItem.Timeline
import com.rahulsengupta.persistence.enitity.CountryHistoricalEntity
import com.rahulsengupta.persistence.enitity.GlobalCountryEntity
import com.rahulsengupta.persistence.usecase.IGetCountryHistoricalUseCase
import com.rahulsengupta.persistence.usecase.IGetGlobalCountryUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface IGetCountryItemsListUseCase {
    val flow: Flow<List<CountryItem>>
}

class GetCountryItemsListUseCase @Inject constructor(
    private val globalCountryUseCase: IGetGlobalCountryUseCase,
    private val countryHistoricalUseCase: IGetCountryHistoricalUseCase,
    private val dispatcher: ICoroutinesDispatcher
) : IGetCountryItemsListUseCase, CoroutineScope {

    private val channel = ConflatedBroadcastChannel<List<CountryItem>>()

    override val coroutineContext: CoroutineContext
        get() = Job() + dispatcher.IO

    @ExperimentalCoroutinesApi
    override val flow: Flow<List<CountryItem>>
        get() = channel.asFlow()

    init {
        CoroutineScope(coroutineContext).launch {
            globalCountryUseCase.flow.combine(countryHistoricalUseCase.flow) { history, timeline ->
                Pair(history, timeline)
            }.collect {
                processCountryListHistorical(it.second, it.first)
            }
        }
    }

    private suspend fun processCountryListHistorical(
        countryTimelineList: List<CountryHistoricalEntity>?,
        countryEntityList: List<GlobalCountryEntity>?
    ) {
        if (countryEntityList != null && countryTimelineList != null) {
            val countryItemsList =
                countryEntityList.sortedByDescending { it.cases }.mapIndexed { index, entity ->
                    val timeline =
                        countryTimelineList.firstOrNull { it.country.equals(entity.country, true) }
                    if (timeline != null) {
                        val dailyCases = timeline.timelineDailyCases.map {
                            Timeline.DateAndCount(
                                it.value,
                                it.key
                            )
                        }
                        val dailyDeaths = timeline.timelineDailyDeaths.map {
                            Timeline.DateAndCount(
                                it.value,
                                it.key
                            )
                        }
                        val dailyRecovered = timeline.timelineDailyRecovered.map {
                            Timeline.DateAndCount(
                                it.value,
                                it.key
                            )
                        }
                        val totalCases = timeline.cases.map {
                            Timeline.DateAndCount(
                                it.value,
                                it.key
                            )
                        }
                        val totalDeaths = timeline.deaths.map {
                            Timeline.DateAndCount(
                                it.value,
                                it.key
                            )
                        }
                        val totalRecovered = timeline.recovered.map {
                            Timeline.DateAndCount(
                                it.value,
                                it.key
                            )
                        }
                        CountryItem(
                            id = index.toLong(),
                            country = entity.country,
                            cases = entity.cases.toString(),
                            flag = requireNotNull(entity.countryInfo.flag),
                            timeline = Timeline(
                                cases = totalCases,
                                dailyCases = dailyCases,
                                deaths = totalDeaths,
                                dailyDeaths = dailyDeaths,
                                recovered = totalRecovered,
                                dailyRecovered = dailyRecovered
                            ),
                            timelineAsList = listOf(
                                dailyCases,
                                totalCases,
                                dailyDeaths,
                                totalDeaths,
                                dailyRecovered,
                                totalRecovered
                            ),
                            dailyList = listOf(
                                CaseType.TodayCases(entity.todayCases),
                                CaseType.TotalCases(entity.cases),
                                CaseType.TodayDeaths(entity.todayDeaths),
                                CaseType.TotalDeaths(entity.deaths),
                                CaseType.TodayRecovered(dailyRecovered.last().count),
                                CaseType.TotalRecovered(entity.recovered)
                            )
                        )
                    } else {
                        null
                    }
                }.filterNotNull()
            channel.send(countryItemsList)
        }
    }
}