package com.rahulsengupta.architecture.android.flows.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.architecture.R
import kotlinx.android.synthetic.main.item_search_country_card.view.*

class SearchCountryCardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list = emptyList<SearchCountryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_country_card, parent, false)
        return SearchCountryCardViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchCountryCardViewHolder).bind(list[position])
    }

    fun setItems(list: List<SearchCountryItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    class SearchCountryCardViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: SearchCountryItem) {
            view.search_country_card_title.text = item.country
        }
    }

}