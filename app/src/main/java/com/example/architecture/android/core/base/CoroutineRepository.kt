package com.example.architecture.android.core.base

import com.example.core.di.ICoroutinesDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class CoroutineRepository constructor(
    private val dispatcher: ICoroutinesDispatcher
) : CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + dispatcher.IO
}