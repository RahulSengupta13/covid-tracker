package com.rahulsengupta.core.usecase

import com.rahulsengupta.persistence.dao.GlobalCountryDao
import com.rahulsengupta.persistence.enitity.GlobalCountryEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

interface IGetGlobalCountryUseCase {
    val flow: Flow<List<GlobalCountryEntity>?>
}

class GetGlobalCountryUseCase @Inject constructor(
    private val globalCountryDao: GlobalCountryDao
) : IGetGlobalCountryUseCase {

    @ExperimentalCoroutinesApi
    override val flow: Flow<List<GlobalCountryEntity>?>
        get() = globalCountryDao.getGlobalCountryResult()

}