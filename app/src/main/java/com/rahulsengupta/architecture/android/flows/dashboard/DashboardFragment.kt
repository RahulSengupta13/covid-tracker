package com.rahulsengupta.architecture.android.flows.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng
import com.rahulsengupta.architecture.R
import com.rahulsengupta.architecture.android.flows.dashboard.model.MapCircle
import com.rahulsengupta.architecture.android.flows.dashboard.model.ViewState.ChartData
import com.rahulsengupta.architecture.android.flows.dashboard.model.ViewState.ChartData.ChartDataValue
import com.rahulsengupta.architecture.databinding.FragmentDashboardBinding
import com.rahulsengupta.core.base.InjectableFragment
import com.rahulsengupta.core.customview.ScrubListener
import com.rahulsengupta.core.extensions.setDefaults
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : InjectableFragment(), OnMapReadyCallback {

    private val viewModel: DashboardViewModel by viewModels { viewModelFactory }
    private val adapter = SparkGlobalTotalsAdapter()

    var lastChartDataValue: ChartDataValue? = null

    lateinit var binding: FragmentDashboardBinding
    lateinit var map: GoogleMap

    private val scrubListener = object : ScrubListener {
        override fun onScrubEndedListener() {
            onScrubbed(lastChartDataValue)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.dashboardGlobalTotalsValue.setDefaults()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.dashboard_live_map) as SupportMapFragment
        mapFragment.run {
            getMapAsync(this@DashboardFragment)
        }

        dashboard_global_totals_sparkview.adapter = adapter
        dashboard_global_totals_sparkview.setScrubListener { onScrubbed(it) }
        dashboard_global_totals_sparkview.listener = scrubListener

        with(news_view_pager) {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = DashboardNewsAdapter()
        }

        with(countries_view_pager) {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = DashboardCountriesAdapter()
        }

        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ChartData -> updateChartData(it)
            }
        })

        viewModel.mapCircles.observe(viewLifecycleOwner, Observer {
            processMapCircles(it)
        })

        //TODO: extract this to a custom view
        binding.customView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return when (event?.action) {
                    ACTION_DOWN -> {
                        dashboard_scrollview.requestDisallowInterceptTouchEvent(true)
                        false
                    }
                    ACTION_UP -> {
                        dashboard_scrollview.requestDisallowInterceptTouchEvent(false)
                        true
                    }
                    ACTION_MOVE -> {
                        dashboard_scrollview.requestDisallowInterceptTouchEvent(true)
                        false
                    }
                    else -> true
                }
            }
        })
    }

    private fun processMapCircles(list: List<MapCircle>?) {
        val latLongs = list?.map { WeightedLatLng(it.center, it.weight) }
        latLongs?.let {
            if(it.isNotEmpty()) {
                val provider = HeatmapTileProvider.Builder()
                    .weightedData(it)
                    .radius(50)
                    .build()
                map.addTileOverlay(TileOverlayOptions().tileProvider(provider))
            }
        }
        /*list?.forEach {
            map.addCircle(CircleOptions().apply {
                center(it.center)
                radius(it.radius*50)
                strokeWidth(0F)
                fillColor(ContextCompat.getColor(requireContext(), com.rahulsengupta.core.R.color.translucentRed))
            })
        }*/
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.run {
            this@DashboardFragment.map = this
            map.uiSettings.run {
                isMapToolbarEnabled = false
            }
            setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.map_skin))
        }
    }

    private fun updateChartData(chartData: ChartData) {
        adapter.update(chartData)
        lastChartDataValue = chartData.list.last()
        onScrubbed(lastChartDataValue)
        dashboard_global_totals_value.textColor =
            ContextCompat.getColor(requireContext(), chartData.color)
        dashboard_global_totals_sparkview.lineColor =
            ContextCompat.getColor(requireContext(), chartData.color)
    }

    private fun onScrubbed(value: Any?) {
        if (value is ChartDataValue) {
            dashboard_global_totals_value.setText(value.count.toString(), true)
            dashboard_global_totals_date.text = value.date
        }
    }
}