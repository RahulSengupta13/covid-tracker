package com.rahulsengupta.core.adapters

import com.rahulsengupta.core.model.CountryItem.Timeline.DateAndCount
import com.robinhood.spark.SparkAdapter

class SparkDateAndCountAdapter : SparkAdapter() {

    private var dateAndCounts: List<DateAndCount> = mutableListOf()

    fun update(value: List<DateAndCount>) {
        dateAndCounts = value
        notifyDataSetChanged()
    }

    override fun getY(index: Int): Float {
        return dateAndCounts[index].count.toFloat()
    }

    override fun getItem(index: Int): DateAndCount {
        return dateAndCounts[index]
    }

    override fun getCount(): Int {
        return dateAndCounts.size
    }
}