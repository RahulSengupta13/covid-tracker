package com.rahulsengupta.architecture.android.flows.dashboard

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.rahulsengupta.architecture.android.flows.dashboard.model.NewsItem

@BindingAdapter("app:newsHeadlineItems")
fun newsHeadlineItems(viewPager: ViewPager2, data: List<NewsItem>?) {
    data?.let {
        (viewPager.adapter as? DashboardNewsAdapter)?.setItems(it)
    }
}