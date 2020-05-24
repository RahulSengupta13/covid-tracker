package com.rahulsengupta.architecture.android.flows.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.architecture.R
import com.rahulsengupta.core.adapters.SparkDateAndCountAdapter
import com.rahulsengupta.core.model.CountryItem
import kotlinx.android.synthetic.main.item_search_country_card_case.view.*
import java.text.NumberFormat

class SearchCountryCardCaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: List<List<CountryItem.Timeline.DateAndCount>> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_country_card_case, parent, false)
        return SearchCountryCardCaseViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchCountryCardCaseViewHolder).bind(list[position], position)
    }

    fun setItems(list: List<List<CountryItem.Timeline.DateAndCount>>) {
        this.list = list
        notifyDataSetChanged()
    }

    class SearchCountryCardCaseViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: List<CountryItem.Timeline.DateAndCount>, position: Int) {
            view.country_casetype_sparkview.adapter = SparkDateAndCountAdapter().apply {
                update(item)
            }
            view.country_case_type_count.text = NumberFormat.getNumberInstance().format(item.last().count)
            view.country_case_type_title.text = when (position) {
                0 -> view.context.getString(R.string.daily_title_cases)
                1 -> view.context.getString(R.string.total_title_cases)
                2 -> view.context.getString(R.string.daily_title_deaths)
                3 -> view.context.getString(R.string.total_title_deaths)
                4 -> view.context.getString(R.string.daily_title_recovered)
                else -> view.context.getString(R.string.total_title_recovered)
            }
        }
    }

}