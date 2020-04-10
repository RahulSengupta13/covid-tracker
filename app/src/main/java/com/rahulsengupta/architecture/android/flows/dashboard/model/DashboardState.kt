package com.rahulsengupta.architecture.android.flows.dashboard.model

import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import com.rahulsengupta.architecture.R
import com.rahulsengupta.architecture.android.flows.dashboard.model.DashBoardChartState.CASES

data class DashboardState(
    val chartState: DashBoardChartState = CASES
)

enum class DashBoardChartState(
    @IdRes val buttonId: Int,
    @StyleRes val titleId: Int,
    val chartAccentId: Int
) {
    CASES(R.id.btnCases, R.string.dashboard_total_title_cases, R.color.red),
    DEATHS(R.id.btnDeaths, R.string.dashboard_total_title_deaths, R.color.red),
    RECOVERED(R.id.btnRecovered, R.string.dashboard_total_title_recovered, R.color.green)
}