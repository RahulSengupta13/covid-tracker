package com.rahulsengupta.architecture.android.flows.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rahulsengupta.architecture.R
import com.rahulsengupta.architecture.android.flows.dashboard.model.ViewState.ChartData
import com.rahulsengupta.architecture.android.flows.dashboard.model.ViewState.ChartData.ChartDataValue
import com.rahulsengupta.architecture.databinding.FragmentDashboardBinding
import com.rahulsengupta.core.base.InjectableFragment
import com.rahulsengupta.core.extensions.setDefaults
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.layout_spark_chart_cardview.*

class DashboardFragment : InjectableFragment() {

    private val viewModel: DashboardViewModel by viewModels { viewModelFactory }
    private val adapter = SparkGlobalTotalsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDashboardBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.dashboardGlobalTotalsValue.setDefaults()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboard_global_totals_sparkview.adapter = adapter
        dashboard_global_totals_sparkview.setScrubListener { onScrubbed(it) }

        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ChartData -> updateChartData(it)
            }
        })
    }

    private fun updateChartData(chartData: ChartData) {
        adapter.update(chartData)
        onScrubbed(chartData.list.last())
    }

    private fun onScrubbed(value: Any?) {
        if (value is ChartDataValue) {
            dashboard_global_totals_value.setText(value.count.toString(), true)
        }
    }
}