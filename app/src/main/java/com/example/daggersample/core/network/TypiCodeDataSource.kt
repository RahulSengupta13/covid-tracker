package com.example.daggersample.core.network

import com.example.daggersample.core.data.BaseDataSource
import javax.inject.Inject

class TypiCodeDataSource @Inject constructor(
    private val service: TypiCodeService
): BaseDataSource() {

    suspend fun getPosts() = getResult { service.getPosts() }
}