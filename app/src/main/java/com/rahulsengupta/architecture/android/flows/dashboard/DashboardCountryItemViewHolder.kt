package com.rahulsengupta.architecture.android.flows.dashboard

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.rahulsengupta.architecture.android.flows.dashboard.model.CountryItem
import com.rahulsengupta.core.base.BaseRecyclerViewHolder
import kotlinx.android.synthetic.main.item_dashboard_countries.view.*

class DashboardCountryItemViewHolder constructor(val view: View) : BaseRecyclerViewHolder(view) {
    private val countryTitle: TextView? = view.country_title
    private val countryCount: TextView? = view.country_count
    private val countryFlag: ImageView? = view.country_image
    private val cardView: MaterialCardView? = view.cardView

    fun bind(headline: CountryItem) {
        countryTitle?.text = headline.country
        countryCount?.text = headline.cases
        countryFlag?.let { Glide.with(view.context).load(headline.flag).into(it) }
        cardView?.setOnClickListener { }
    }
}