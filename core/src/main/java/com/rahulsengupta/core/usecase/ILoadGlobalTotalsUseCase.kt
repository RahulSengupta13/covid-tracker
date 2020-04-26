package com.rahulsengupta.core.usecase

import com.rahulsengupta.network.usecase.IFetchGlobalTotalsUseCase
import com.rahulsengupta.persistence.dao.GlobalTotalsDao
import com.rahulsengupta.persistence.enitity.GlobalTotalsEntity
import javax.inject.Inject

interface ILoadGlobalTotalsUseCase {
    suspend fun loadGlobalTotals()
    suspend fun hasGlobalTotals(): Boolean
}

class LoadGlobalTotalsUseCase @Inject constructor(
    private val useCase: IFetchGlobalTotalsUseCase,
    private val dao: GlobalTotalsDao
) : ILoadGlobalTotalsUseCase {

    override suspend fun loadGlobalTotals() {
        val globalTotals = useCase.fetchGlobalTotals() ?: return
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
        dao.insertOrReplace(item = globalTotalsEntity)
    }

    override suspend fun hasGlobalTotals() = dao.getGlobalTotalsCount() > 0
}