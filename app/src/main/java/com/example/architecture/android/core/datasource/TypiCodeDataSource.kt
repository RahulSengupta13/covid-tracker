package com.example.architecture.android.core.datasource

import com.example.architecture.android.core.base.BaseDataSource
import com.example.network.TypiCodeService
import javax.inject.Inject

class TypiCodeDataSource @Inject constructor(
    private val service: TypiCodeService
): BaseDataSource() {

    suspend fun getPosts() = getResult { service.getPosts() }
}