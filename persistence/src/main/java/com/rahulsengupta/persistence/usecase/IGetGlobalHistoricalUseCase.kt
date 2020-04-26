package com.rahulsengupta.persistence.usecase

import com.rahulsengupta.persistence.dao.GlobalHistoricalDao
import com.rahulsengupta.persistence.enitity.GlobalHistoricalEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

interface IGetGlobalHistoricalUseCase {
    val flow: Flow<GlobalHistoricalEntity?>
}

class GetGlobalHistoricalUseCase @Inject constructor(
    private val globalHistoricalDao: GlobalHistoricalDao
) : IGetGlobalHistoricalUseCase {

    @ExperimentalCoroutinesApi
    override val flow: Flow<GlobalHistoricalEntity?>
        get() = globalHistoricalDao.getGlobalHistorical().distinctUntilChanged()
}