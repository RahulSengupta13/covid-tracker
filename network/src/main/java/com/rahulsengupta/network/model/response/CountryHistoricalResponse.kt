package com.rahulsengupta.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CountryHistoricalResponse(
    val country: String,
    val province: String? = "",
    val timeline: Timeline
) {

    @Serializable
    data class Timeline(
        val cases: Map<String, Int>,
        val deaths: Map<String, Int>,
        val recovered: Map<String, Int>
    )
}