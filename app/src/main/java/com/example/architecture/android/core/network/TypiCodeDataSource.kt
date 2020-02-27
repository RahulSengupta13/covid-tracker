package com.example.architecture.android.core.network

import com.example.architecture.android.core.data.BaseDataSource
import javax.inject.Inject

class TypiCodeDataSource @Inject constructor(
    private val service: TypiCodeService
): BaseDataSource() {

    suspend fun getPosts() = getResult { service.getPosts() }
}