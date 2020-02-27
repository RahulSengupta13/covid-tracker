package com.example.architecture.android.core.network.websocket.model.channel

import java.io.Serializable

data class Ticker(
    val time: String?,
    val price: String?
): Serializable