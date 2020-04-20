package com.rahulsengupta.architecture.android.flows.dashboard

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.rahulsengupta.architecture.android.flows.dashboard.model.NewsItem.Headline
import com.rahulsengupta.core.base.BaseRecyclerViewHolder
import kotlinx.android.synthetic.main.item_news_headline.view.*

class DashboardNewsItemHeadlineViewHolder constructor(val view: View) : BaseRecyclerViewHolder(view) {
    private val newsTitle: TextView? = view.news_title
    private val newsPublishedAt: TextView? = view.news_published_at
    private val newsImageView: ImageView? = view.news_image
    private val cardView: MaterialCardView? = view.cardView

    fun bind(headline: Headline) {
        newsTitle?.text = headline.title
        newsPublishedAt?.text = headline.publishedAt
        newsImageView?.let { Glide.with(view.context).load(headline.imageUrl).into(it) }
        cardView?.setOnClickListener {  }
    }
}