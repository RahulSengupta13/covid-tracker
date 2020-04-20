package com.rahulsengupta.architecture.android.flows.dashboard

import com.rahulsengupta.architecture.android.flows.dashboard.model.CountryItem.Timeline.DateAndCount
import com.robinhood.spark.SparkAdapter

class SparkCountriesAdapter : SparkAdapter() {

    private var dateAndCounts: List<DateAndCount> = mutableListOf()

    fun update(value: List<DateAndCount>) {
        dateAndCounts = value
        notifyDataSetChanged()
    }

    override fun getY(index: Int): Float {
        return dateAndCounts[index].count.toFloat() ?: 0F
    }

    override fun getItem(index: Int): DateAndCount {
        return dateAndCounts[index]
    }

    override fun getCount(): Int {
        return dateAndCounts.size
    }
}