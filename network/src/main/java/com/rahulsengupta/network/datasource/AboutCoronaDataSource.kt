package com.rahulsengupta.network.datasource

import com.rahulsengupta.network.base.BaseDataSource
import com.rahulsengupta.network.services.AboutCoronaService
import javax.inject.Inject

class AboutCoronaDataSource @Inject constructor(
    private val aboutCoronaService: AboutCoronaService
) : BaseDataSource() {

    suspend fun getTimeline() = getResult { aboutCoronaService.getTimeline() }

}