package com.rahulsengupta.architecture.android.core.datasource

import com.rahulsengupta.network.base.BaseDataSource
import com.rahulsengupta.network.services.TypiCodeService
import javax.inject.Inject

class TypiCodeDataSource @Inject constructor(
    private val service: TypiCodeService
): BaseDataSource() {

    suspend fun getPosts() = getResult { service.getPosts() }
}