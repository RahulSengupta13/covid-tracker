package com.example.daggersample.core.di.modules

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class CoroutinesModule {

    @Provides
    fun providesDispatchers(): ICoroutinesDispatcher =
        CoroutinesDispatcher
}

interface ICoroutinesDispatcher {

    val IO: CoroutineDispatcher

    val MAIN: CoroutineDispatcher

    val DEFAULT: CoroutineDispatcher
}

object CoroutinesDispatcher :
    ICoroutinesDispatcher {

    override val IO: CoroutineDispatcher
        get() = Dispatchers.IO

    override val MAIN: CoroutineDispatcher
        get() = Dispatchers.Main

    override val DEFAULT: CoroutineDispatcher
        get() = Dispatchers.Default
}