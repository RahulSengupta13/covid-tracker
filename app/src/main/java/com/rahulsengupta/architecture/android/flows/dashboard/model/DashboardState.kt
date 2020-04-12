package com.rahulsengupta.architecture.android.flows.dashboard.model

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.rahulsengupta.architecture.R
import com.rahulsengupta.architecture.android.flows.dashboard.model.DashBoardChartModeState.DAILY
import com.rahulsengupta.architecture.android.flows.dashboard.model.DashBoardChartState.CASES

data class DashboardState(
    val chartState: DashBoardChartState = CASES,
    val chartModeState: DashBoardChartModeState = DAILY
)

enum class DashBoardChartState(
    @IdRes val typeButtonId: Int,
    @StringRes val titleIdTotal: Int,
    @StringRes val titleIdDaily: Int,
    val chartAccentId: Int
) {
    CASES(
        R.id.btnCases,
        R.string.dashboard_total_title_cases,
        R.string.dashboard_daily_title_cases,
        R.color.red
    ),
    DEATHS(
        R.id.btnDeaths,
        R.string.dashboard_total_title_deaths,
        R.string.dashboard_daily_title_deaths,
        R.color.red
    ),
    RECOVERED(
        R.id.btnRecovered,
        R.string.dashboard_total_title_recovered,
        R.string.dashboard_daily_title_recovered,
        R.color.green
    )
}

enum class DashBoardChartModeState(
    @IdRes val modeButtonId: Int
) {
    DAILY(R.id.btnDaily),
    TOTAL(R.id.btnTotal)
}