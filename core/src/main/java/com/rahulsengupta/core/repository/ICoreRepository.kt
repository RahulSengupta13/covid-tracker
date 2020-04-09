package com.rahulsengupta.core.repository

import com.rahulsengupta.network.datasource.NovelCovid19DataSource
import com.rahulsengupta.persistence.dao.GlobalTotalsDao
import com.rahulsengupta.persistence.enitity.GlobalTotalsEntity
import javax.inject.Inject

interface ICoreRepository {

    suspend fun initialize()

}

class CoreRepository @Inject constructor(
    private val dataSource: NovelCovid19DataSource,
    private val globalTotalsDao: GlobalTotalsDao
) : ICoreRepository {

    override suspend fun initialize() {
        initializeGlobalTotals()
    }

    private suspend fun initializeGlobalTotals() {
        val globalTotals = dataSource.getGlobalTotals().data ?: return
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