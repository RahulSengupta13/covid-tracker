package com.rahulsengupta.architecture.android.flows.dashboard

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.rahulsengupta.architecture.android.flows.dashboard.model.NewsItem

@BindingAdapter("app:newsHeadlineItems")
fun newsHeadlineItems(viewPager: RecyclerView, data: List<NewsItem>?) {
    data?.let {
        (viewPager.adapter as? DashboardNewsAdapter)?.setItems(it)
    }
}