package com.rahulsengupta.architecture.android.flows.dashboard

import android.transition.TransitionManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.rahulsengupta.architecture.android.core.extensions.loadImage
import com.rahulsengupta.core.adapters.SparkDateAndCountAdapter
import com.rahulsengupta.core.model.CountryItem
import com.rahulsengupta.core.base.BaseRecyclerViewHolder
import com.robinhood.spark.SparkView
import kotlinx.android.synthetic.main.item_dashboard_countries.view.*

class DashboardCountryItemViewHolder(
    val view: View,
    private val recyclerView: RecyclerView,
    private val listener: Listener
) : BaseRecyclerViewHolder(view) {
    private val moreInfo: TextView? = view.country_more
    private val countryTitle: TextView? = view.country_title
    private val countryCount: TextView? = view.country_count
    private val countryFlag: ImageView? = view.country_image
    private val collapsedView: ConstraintLayout? = view.collapsed_view
    private val expandedView: ConstraintLayout? = view.expanded_view
    private val sparkChart: SparkView = view.country_chart

    fun bind(index: Int, item: CountryItem) {
        countryTitle?.text = item.country
        countryCount?.text = item.cases
        countryFlag?.loadImage(item.flag) {
            expandedView?.background = it
        }
        collapsedView?.visibility = View.VISIBLE
        expandedView?.visibility = View.GONE
        expandedView?.setOnClickListener {
//            val viewSharedAxis: MaterialSharedAxis = MaterialSharedAxis.create(view.context, MaterialSharedAxis.Y, false)
//            TransitionManager.beginDelayedTransition(recyclerView, viewSharedAxis)
            expandedView.visibility = View.GONE
            collapsedView?.visibility = View.VISIBLE
            TransitionManager.beginDelayedTransition(recyclerView)
        }
        collapsedView?.setOnClickListener {
//            val viewSharedAxis: MaterialSharedAxis = MaterialSharedAxis.create(view.context, MaterialSharedAxis.Y, true)
//            TransitionManager.beginDelayedTransition(recyclerView, viewSharedAxis)
            collapsedView.visibility = View.GONE
            expandedView?.visibility = View.VISIBLE
            TransitionManager.beginDelayedTransition(recyclerView)
        }
        sparkChart.run {
            adapter = SparkDateAndCountAdapter().apply {
                update(item.timeline.dailyCases)
            }
            isScrubEnabled = false
        }

        moreInfo?.setOnClickListener {
            listener.onCountryMoreClicked(index)
        }

    }

    interface Listener {
        fun onCountryMoreClicked(index: Int)
    }
}