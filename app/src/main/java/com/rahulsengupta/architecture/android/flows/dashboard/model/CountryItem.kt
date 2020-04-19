package com.rahulsengupta.architecture.android.flows.dashboard.model

data class CountryItem(
    val country: String,
    val cases: String,
    val flag: String,
    val timeline: Timeline
) {

    data class Timeline(
        val cases: List<DateAndCount>,
        val deaths: List<DateAndCount>,
        val recovered: List<DateAndCount>
    ) {

        data class DateAndCount(
            val count: Int,
            val date: String
        )
    }
}