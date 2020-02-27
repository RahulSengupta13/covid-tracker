package com.example.daggersample.core.network.websocket.model.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TickerRequest(
    val name: String = "ticker",
    @SerializedName("product_ids")
    val productIds: List<String>
) : Serializable