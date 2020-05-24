package com.rahulsengupta.core.model

import com.rahulsengupta.core.R

data class CountryItem(
    val id: Long,
    val country: String,
    val cases: String,
    val flag: String,
    val timeline: Timeline,
    val timelineAsList: List<List<Timeline.DateAndCount>>,
    val dailyList: List<CaseType>
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

    sealed class CaseType(
        open val count: Int,
        open val caseTitle: Int
    ) {

        data class TodayCases(
            override val count: Int,
            override val caseTitle: Int = R.string.search_country_cases_today
        ) : CaseType(count, caseTitle)

        data class TodayDeaths(
            override val count: Int,
            override val caseTitle: Int = R.string.search_country_deaths_today
        ) : CaseType(count, caseTitle)

        data class TodayRecovered(
            override val count: Int,
            override val caseTitle: Int = R.string.search_country_recovered_today
        ) : CaseType(count, caseTitle)

        data class TotalCases(
            override val count: Int,
            override val caseTitle: Int = R.string.search_country_cases_total
        ) : CaseType(count, caseTitle)

        data class TotalDeaths(
            override val count: Int,
            override val caseTitle: Int = R.string.search_country_deaths_total
        ) : CaseType(count, caseTitle)

        data class TotalRecovered(
            override val count: Int,
            override val caseTitle: Int = R.string.search_country_recovered_total
        ) : CaseType(count, caseTitle)
    }
}