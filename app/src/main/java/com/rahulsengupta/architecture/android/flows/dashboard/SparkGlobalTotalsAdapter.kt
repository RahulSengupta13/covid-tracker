package com.rahulsengupta.architecture.android.flows.dashboard

import android.graphics.RectF
import com.rahulsengupta.architecture.android.flows.dashboard.model.ViewState.ChartData
import com.rahulsengupta.architecture.android.flows.dashboard.model.ViewState.ChartData.ChartDataValue
import com.robinhood.spark.SparkAdapter

class SparkGlobalTotalsAdapter : SparkAdapter() {

    private var globalHistorical: List<ChartDataValue> = mutableListOf()

    fun update(value: ChartData) {
        globalHistorical = value.list
        notifyDataSetChanged()
    }

    override fun getY(index: Int): Float {
        return globalHistorical[index].count.toFloat()
    }

    override fun getItem(index: Int): ChartDataValue {
        return globalHistorical[index]
    }

    override fun getCount(): Int {
        return globalHistorical.size
    }
}