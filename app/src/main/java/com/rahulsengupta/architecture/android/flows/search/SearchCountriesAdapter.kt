package com.rahulsengupta.architecture.android.flows.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.architecture.R
import kotlinx.android.synthetic.main.item_search_country.view.*

class SearchCountriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list = emptyList<SearchCountryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_country, parent, false)
        return SearchCountryViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchCountryViewHolder).bind(list[position])
    }

    fun setItems(list: List<SearchCountryItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    class SearchCountryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: SearchCountryItem) {
            view.item_search_country.text = item.country
//            view.item_search_country_flag.loadImage(item.flag)
        }
    }
}