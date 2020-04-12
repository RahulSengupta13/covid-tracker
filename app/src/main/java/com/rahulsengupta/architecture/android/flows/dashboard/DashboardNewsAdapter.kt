package com.rahulsengupta.architecture.android.flows.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.architecture.R
import com.rahulsengupta.architecture.android.flows.dashboard.model.NewsItem
import com.rahulsengupta.architecture.android.flows.dashboard.model.NewsItemViewType
import com.rahulsengupta.core.base.BaseRecyclerViewHolder

class DashboardNewsAdapter : RecyclerView.Adapter<BaseRecyclerViewHolder>() {

    private var list: List<NewsItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder {
        return when (viewType) {
            NewsItemViewType.HEADLINE.viewType -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_news_headline, parent, false)
                DashboardNewsItemHeadlineViewHolder(itemView)
            }
            else -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_news_more, parent, false)
                DashboardNewsItemMoreViewHolder(itemView)
            }
        }

    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        when (val item = list[position]) {
            is NewsItem.Headline -> (holder as? DashboardNewsItemHeadlineViewHolder)?.bind(item)
            is NewsItem.More -> (holder as? DashboardNewsItemMoreViewHolder)?.bind()
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int) = list[position].viewType

    fun setItems(list: List<NewsItem>) {
        this.list = list
        notifyDataSetChanged()
    }
}
