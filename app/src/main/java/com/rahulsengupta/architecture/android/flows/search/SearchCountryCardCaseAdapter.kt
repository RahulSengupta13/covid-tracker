package com.rahulsengupta.architecture.android.flows.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.architecture.R
import com.rahulsengupta.core.model.CountryItem
import kotlinx.android.synthetic.main.item_search_country_card_case.view.*
import java.text.NumberFormat

class SearchCountryCardCaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list = emptyList<CountryItem.CaseType>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_country_card_case, parent, false)
        return SearchCountryCardCaseViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchCountryCardCaseViewHolder).bind(list[position])
    }

    fun setItems(list: List<CountryItem.CaseType>) {
        this.list = list
        notifyDataSetChanged()
    }

    class SearchCountryCardCaseViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: CountryItem.CaseType) {
            view.country_case_type_title.text = view.context.getString(item.caseTitle)
            view.country_case_type_count.text = NumberFormat.getNumberInstance().format(item.count)
        }
    }

}