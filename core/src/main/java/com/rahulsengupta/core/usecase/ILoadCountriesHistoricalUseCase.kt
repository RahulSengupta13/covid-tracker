package com.rahulsengupta.core.usecase

import com.rahulsengupta.network.usecase.IFetchCountriesHistoricalUseCase
import com.rahulsengupta.persistence.dao.CountryHistoricalDao
import com.rahulsengupta.persistence.enitity.CountryHistoricalEntity
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
            CountryHistoricalEntity(
                country = it.country,
                province = it.province ?: "",
                cases = it.timeline.cases,
                deaths = it.timeline.deaths,
                recovered = it.timeline.recovered
            )
        }
        dao.insertAllOrReplace(countriesHistoricalEntities)
    }

    override suspend fun hasCountriesHistorical() = dao.getCountryHistoricalCount() > 0
}