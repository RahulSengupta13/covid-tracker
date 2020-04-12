package com.rahulsengupta.architecture.android.flows.dashboard.model

sealed class ViewState {

    data class ChartData(
        val list: List<ChartDataValue>,
        val color: Int
    ) : ViewState() {

        data class ChartDataValue(
            val date: String,
            val count: Int
        )
    }
}