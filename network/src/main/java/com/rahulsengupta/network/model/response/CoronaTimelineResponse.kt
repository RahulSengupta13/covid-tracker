package com.rahulsengupta.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoronaTimelineResponse(
    @SerialName("data")
    val data: List<CoronaTimelineValue>
) {

    @Serializable
    data class CoronaTimelineValue(
        @SerialName("new_confirmed")
        val newConfirmed: Int,
        @SerialName("new_recovered")
        val newRecovered: Int,
        @SerialName("new_deaths")
        val newDeaths: Int,
        @SerialName("updated_at")
        val updatedAt: String
    )
}