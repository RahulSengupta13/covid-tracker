package com.rahulsengupta.core.usecase

import com.rahulsengupta.persistence.dao.CountryHistoricalDao
import com.rahulsengupta.persistence.dao.GlobalHistoricalDao
import com.rahulsengupta.persistence.enitity.CountryHistoricalEntity
import com.rahulsengupta.persistence.enitity.GlobalHistoricalEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

interface IGetCountryHistoricalUseCase {
    val flow: Flow<List<CountryHistoricalEntity>?>
}

class GetCountryHistoricalUseCase @Inject constructor(
    private val countryHistoricalDao: CountryHistoricalDao
) : IGetCountryHistoricalUseCase {

    @ExperimentalCoroutinesApi
    override val flow: Flow<List<CountryHistoricalEntity>?>
        get() = countryHistoricalDao.getCountryHistorical().distinctUntilChanged()
}