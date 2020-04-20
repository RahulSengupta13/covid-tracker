package com.rahulsengupta.architecture.android.flows.dashboard

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.rahulsengupta.architecture.android.flows.dashboard.model.NewsItem.Headline
import com.rahulsengupta.core.base.BaseRecyclerViewHolder
import kotlinx.android.synthetic.main.item_news_headline.view.*

class DashboardNewsItemMoreViewHolder constructor(val view: View) : BaseRecyclerViewHolder(view) {
    private val cardView: MaterialCardView? = view.cardView

    fun bind() {
        cardView?.setOnClickListener {  }
    }
}