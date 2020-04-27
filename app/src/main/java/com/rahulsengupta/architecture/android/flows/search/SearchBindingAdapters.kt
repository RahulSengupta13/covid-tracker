package com.rahulsengupta.architecture.android.flows.search

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("app:setSearchCountries")
fun setSearchCountries(recyclerView: RecyclerView, data: List<SearchCountryItem>?) {
    data?.let {
        (recyclerView.adapter as? SearchCountriesAdapter)?.setItems(it)
    }
}