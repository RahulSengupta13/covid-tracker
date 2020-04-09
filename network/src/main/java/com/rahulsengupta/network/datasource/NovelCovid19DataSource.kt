package com.rahulsengupta.network.datasource

import com.rahulsengupta.network.base.BaseDataSource
import com.rahulsengupta.network.services.NovelCovid19Service
import javax.inject.Inject

class NovelCovid19DataSource @Inject constructor(
    private val service: NovelCovid19Service
): BaseDataSource() {

    suspend fun getGlobalTotals() = getResult { service.getGlobalTotals() }
}