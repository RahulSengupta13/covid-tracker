package com.rahulsengupta.core.usecase

import com.rahulsengupta.network.usecase.IFetchGlobalCountryUseCase
import com.rahulsengupta.persistence.dao.GlobalCountryDao
import com.rahulsengupta.persistence.enitity.GlobalCountryEntity
import javax.inject.Inject

interface ILoadGlobalCountryUseCase {
    suspend fun loadGlobalCountry(sortBy: String)
    suspend fun hasGlobalCountry(): Boolean
}

class LoadGlobalCountryUseCase @Inject constructor(
    private val useCase: IFetchGlobalCountryUseCase,
    private val dao: GlobalCountryDao
) : ILoadGlobalCountryUseCase {

    override suspend fun loadGlobalCountry(sortBy: String) {
        val globalCountryResult = useCase.fetchGlobalCountry(sortBy) ?: return
        val globalCountryResultEntities = globalCountryResult
            .filter { it.countryInfo.id != null && it.country.isNotEmpty() }
            .map {
                GlobalCountryEntity(
                    it.active,
                    it.cases,
                    it.casesPerOneMillion,
                    it.country,
                    GlobalCountryEntity.CountryInfo(
                        it.countryInfo.flag,
                        it.countryInfo.id,
                        it.countryInfo.iso2,
                        it.countryInfo.iso3,
                        it.countryInfo.lat,
                        it.countryInfo.long
                    ),
                    it.critical,
                    it.deaths,
                    it.deathsPerOneMillion,
                    it.recovered,
                    it.todayCases,
                    it.todayDeaths,
                    it.updated
                )
            }
        dao.insertAllOrReplace(globalCountryResultEntities)
    }

    override suspend fun hasGlobalCountry() = dao.getGlobalCountryCount() > 0
}