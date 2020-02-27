package com.example.daggersample.core.network.websocket.model.channel

import java.io.Serializable

data class Ticker(
    val time: String?,
    val price: String?
): Serializable