package com.rahulsengupta.architecture.android.flows.search

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.rahulsengupta.core.model.CountryItem

@BindingAdapter("app:setSearchCountries")
fun setSearchCountries(recyclerView: RecyclerView, data: List<CountryItem>?) {
    data?.let {
        (recyclerView.adapter as? SearchCountriesAdapter)?.setItems(it)
    }
}

@BindingAdapter("app:setSearchCountries")
fun setSearchCountries(viewPager: ViewPager2, data: List<CountryItem>?) {
    data?.let {
        (viewPager.adapter as? SearchCountryCardAdapter)?.setItems(it)
    }
}

@BindingAdapter("app:setItemPosition")
fun setItemPosition(viewPager: ViewPager2, position: Int?) {
    position?.let {
        viewPager.currentItem = it
    }
}