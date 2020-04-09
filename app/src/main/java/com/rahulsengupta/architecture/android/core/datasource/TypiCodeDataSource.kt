package com.rahulsengupta.architecture.android.core.datasource

import com.rahulsengupta.architecture.android.core.base.BaseDataSource
import com.rahulsengupta.network.TypiCodeService
import javax.inject.Inject

class TypiCodeDataSource @Inject constructor(
    private val service: TypiCodeService
): BaseDataSource() {

    suspend fun getPosts() = getResult { service.getPosts() }
}