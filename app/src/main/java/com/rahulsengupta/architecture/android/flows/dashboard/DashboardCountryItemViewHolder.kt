package com.rahulsengupta.architecture.android.flows.dashboard

import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialSharedAxis
import com.rahulsengupta.architecture.android.flows.dashboard.model.CountryItem
import com.rahulsengupta.core.base.BaseRecyclerViewHolder
import com.rahulsengupta.core.extensions.loadImage
import com.robinhood.spark.SparkView
import kotlinx.android.synthetic.main.item_dashboard_countries.view.*

class DashboardCountryItemViewHolder(
    val view: View,
    val recyclerView: RecyclerView
) : BaseRecyclerViewHolder(view) {
    private val countryTitle: TextView? = view.country_title
    private val countryCount: TextView? = view.country_count
    private val countryFlag: ImageView? = view.country_image
    private val collapsedView: ConstraintLayout? = view.collapsed_view
    private val expandedView: ConstraintLayout? = view.expanded_view
    private val sparkChart: SparkView = view.country_chart

    fun bind(item: CountryItem) {
        countryTitle?.text = item.country
        countryCount?.text = item.cases
        countryFlag?.loadImage(item.flag) {
            expandedView?.background = it
        }
        collapsedView?.visibility = View.VISIBLE
        expandedView?.visibility = View.GONE
        expandedView?.setOnClickListener {
            val viewSharedAxis: MaterialSharedAxis =
                MaterialSharedAxis.create(view.context, MaterialSharedAxis.X, false)
            collapsedView?.visibility = View.VISIBLE
            expandedView.visibility = View.GONE
            TransitionManager.beginDelayedTransition(recyclerView)
        }
        collapsedView?.setOnClickListener {
            val viewSharedAxis: MaterialSharedAxis =
                MaterialSharedAxis.create(view.context, MaterialSharedAxis.X, true)
            collapsedView.visibility = View.GONE
            expandedView?.visibility = View.VISIBLE
            TransitionManager.beginDelayedTransition(recyclerView)
        }
        sparkChart.adapter = SparkCountriesAdapter().apply {
            update(item.timeline.cases)
        }
        sparkChart.isScrubEnabled = false
    }
}