package com.rahulsengupta.architecture.android.flows.dashboard.model

data class CountryItem(
    val id: Long,
    val country: String,
    val cases: String,
    val flag: String,
    val timeline: Timeline
) {

    data class Timeline(
        val cases: List<DateAndCount>,
        val dailyCases: List<DateAndCount>,
        val deaths: List<DateAndCount>,
        val dailyDeaths: List<DateAndCount>,
        val recovered: List<DateAndCount>,
        val dailyRecovered: List<DateAndCount>
    ) {

        data class DateAndCount(
            val count: Int,
            val date: String
        )
    }
}