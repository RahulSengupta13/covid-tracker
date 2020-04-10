package com.rahulsengupta.architecture.android.flows.dashboard.model

sealed class ViewState {

    data class ChartData(
        val list: List<ChartDataValue>
    ) : ViewState() {

        data class ChartDataValue(
            val date: String,
            val count: Int
        )
    }
}