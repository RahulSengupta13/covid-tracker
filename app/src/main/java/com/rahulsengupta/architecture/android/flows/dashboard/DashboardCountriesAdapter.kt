package com.rahulsengupta.architecture.android.flows.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.architecture.R
import com.rahulsengupta.core.model.CountryItem
import com.rahulsengupta.core.base.BaseRecyclerViewHolder

class DashboardCountriesAdapter(private val recyclerView: RecyclerView, private val listener: Listener) : RecyclerView.Adapter<BaseRecyclerViewHolder>() {

    init {
        setHasStableIds(true)
    }

    private var list: List<CountryItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard_countries, parent, false)
        return DashboardCountryItemViewHolder(itemView, recyclerView, listener)
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        (holder as? DashboardCountryItemViewHolder)?.bind(position, list[position])
    }

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int) = list[position].id

    fun setItems(list: List<CountryItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    interface Listener: DashboardCountryItemViewHolder.Listener
}
