package com.rahulsengupta.core.usecase

import com.rahulsengupta.core.extensions.getFormattedDateFromShortPattern
import com.rahulsengupta.network.usecase.IFetchGlobalHistoricalUseCase
import com.rahulsengupta.persistence.dao.GlobalHistoricalDao
import com.rahulsengupta.persistence.enitity.GlobalHistoricalEntity
import javax.inject.Inject

interface ILoadGlobalHistoricalUseCase {
    suspend fun loadGlobalHistorical(lastDays: Int)
    suspend fun hasGlobalHistorical(): Boolean
}

class LoadGlobalHistoricalUseCase @Inject constructor(
    private val useCase: IFetchGlobalHistoricalUseCase,
    private val dao: GlobalHistoricalDao
) : ILoadGlobalHistoricalUseCase {

    override suspend fun loadGlobalHistorical(lastDays: Int) {
        val globalHistorical = useCase.fetchGlobalHistorical(lastDays) ?: return
        val globalHistoricalEntity = GlobalHistoricalEntity(
            cases = globalHistorical.cases.map { (k, v) -> k.getFormattedDateFromShortPattern() to v }
                .toMap(),
            deaths = globalHistorical.deaths.map { (k, v) -> k.getFormattedDateFromShortPattern() to v }
                .toMap(),
            recovered = globalHistorical.recovered.map { (k, v) -> k.getFormattedDateFromShortPattern() to v }
                .toMap()
        )
        dao.insertOrReplace(globalHistoricalEntity)
    }

    override suspend fun hasGlobalHistorical() = dao.getGlobalHistoricalCount() > 0
}