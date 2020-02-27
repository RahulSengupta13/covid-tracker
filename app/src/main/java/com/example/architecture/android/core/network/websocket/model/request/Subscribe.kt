package com.example.architecture.android.core.network.websocket.model.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Subscribe(
    val type: String = "subscribe",
    @SerializedName("product_ids")
    val productIds: List<String>,
    val channels: List<TickerRequest>
) : Serializable