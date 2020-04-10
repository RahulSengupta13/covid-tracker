package com.rahulsengupta.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class GlobalHistoricalResponse(
    val cases: Map<String, Int>,
    val deaths: Map<String, Int>,
    val recovered: Map<String, Int>
)