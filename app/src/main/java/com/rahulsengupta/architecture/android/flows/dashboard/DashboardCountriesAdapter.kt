package com.rahulsengupta.architecture.android.flows.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.architecture.R
import com.rahulsengupta.architecture.android.flows.dashboard.model.CountryItem
import com.rahulsengupta.core.base.BaseRecyclerViewHolder

class DashboardCountriesAdapter(private val recyclerView: RecyclerView) : RecyclerView.Adapter<BaseRecyclerViewHolder>() {

    init {
        setHasStableIds(true)
    }

    private var list: List<CountryItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard_countries, parent, false)
        return DashboardCountryItemViewHolder(itemView, recyclerView)
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        (holder as? DashboardCountryItemViewHolder)?.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int) = list[position].id

    fun setItems(list: List<CountryItem>) {
        this.list = list
        notifyDataSetChanged()
    }
}
