package com.rahulsengupta.core.usecase

import com.rahulsengupta.network.usecase.IFetchCountriesHistoricalUseCase
import com.rahulsengupta.persistence.dao.CountryHistoricalDao
import com.rahulsengupta.persistence.enitity.CountryHistoricalEntity
import timber.log.Timber
import javax.inject.Inject

interface ILoadCountriesHistoricalUseCase {
    suspend fun loadCountriesHistorical(lastDays: Int)
    suspend fun hasCountriesHistorical(): Boolean
}

class LoadCountriesHistoricalUseCase @Inject constructor(
    private val useCase: IFetchCountriesHistoricalUseCase,
    private val dao: CountryHistoricalDao
) : ILoadCountriesHistoricalUseCase {

    override suspend fun loadCountriesHistorical(lastDays: Int) {
        val countriesHistoricalResult = useCase.fetchCountriesHistorical(lastDays) ?: return
        val countriesHistoricalEntities = countriesHistoricalResult.map {

            val casesAsList = it.timeline.cases.toList()
            val dailyCasesTemp = casesAsList.mapIndexed { index, pair ->
                if(index == 0) {
                    pair
                } else {
                    Pair(pair.first, pair.second - casesAsList[index - 1].second)
                }
            }
            val dailyCases = dailyCasesTemp.takeLast(dailyCasesTemp.size - 1).toMap()

            val deathsAsList = it.timeline.deaths.toList()
            val dailyDeathsTemp = deathsAsList.mapIndexed { index, pair ->
                if(index == 0) {
                    pair
                } else {
                    Pair(pair.first, pair.second - deathsAsList[index - 1].second)
                }
            }
            val dailyDeaths = dailyDeathsTemp.takeLast(dailyDeathsTemp.size - 1).toMap()

            val recoveredAsList = it.timeline.recovered.toList()
            val dailyRecoveredTemp = recoveredAsList.mapIndexed { index, pair ->
                if(index == 0) {
                    pair
                } else {
                    Pair(pair.first, pair.second - recoveredAsList[index - 1].second)
                }
            }
            val dailyRecovered = dailyRecoveredTemp.takeLast(dailyRecoveredTemp.size - 1).toMap()

            CountryHistoricalEntity(
                country = it.country,
                province = it.province ?: "",
                cases = it.timeline.cases,
                timelineDailyCases = dailyCases,
                deaths = it.timeline.deaths,
                timelineDailyDeaths = dailyDeaths,
                recovered = it.timeline.recovered,
                timelineDailyRecovered = dailyRecovered
            )
        }
        dao.insertAllOrReplace(countriesHistoricalEntities)
    }

    override suspend fun hasCountriesHistorical() = dao.getCountryHistoricalCount() > 0
}