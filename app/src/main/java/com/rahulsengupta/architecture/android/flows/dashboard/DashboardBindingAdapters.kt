package com.rahulsengupta.architecture.android.flows.dashboard

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.core.model.CountryItem
import com.rahulsengupta.architecture.android.flows.dashboard.model.NewsItem

@BindingAdapter("app:newsHeadlineItems")
fun newsHeadlineItems(viewPager: RecyclerView, data: List<NewsItem>?) {
    data?.let {
        (viewPager.adapter as? DashboardNewsAdapter)?.setItems(it)
    }
}

@BindingAdapter("app:countryItems")
fun countryItems(viewPager: RecyclerView, data: List<CountryItem>?) {
    data?.let {
        (viewPager.adapter as? DashboardCountriesAdapter)?.setItems(it)
    }
}