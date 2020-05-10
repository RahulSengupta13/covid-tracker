package com.rahulsengupta.architecture.android.flows.dashboard

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import com.robinhood.spark.animation.LineSparkAnimator
import com.robinhood.spark.animation.MorphSparkAnimator

class DashboardFragment : InjectableFragment(), OnMapReadyCallback, DashboardCountriesAdapter.Listener {

    private val viewModel: DashboardViewModel by viewModels { viewModelFactory }
    private val adapter = SparkGlobalTotalsAdapter()

    var lastChartDataValue: ChartDataValue? = null

    lateinit var binding: FragmentDashboardBinding
    private var map: GoogleMap? = null

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

        binding.dashboardConstraintLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        val mapFragment = childFragmentManager.findFragmentById(R.id.dashboard_live_map) as SupportMapFragment
        mapFragment.run {
            getMapAsync(this@DashboardFragment)
        }

        binding.dashboardGlobalTotalsSparkview.run {
            adapter = this@DashboardFragment.adapter
            setScrubListener { onScrubbed(it) }
            listener = this@DashboardFragment.scrubListener
            sparkAnimator = MorphSparkAnimator()
        }

        with(binding.newsViewPager) {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = DashboardNewsAdapter()
        }

        with(binding.countriesViewPager) {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = DashboardCountriesAdapter(this, this@DashboardFragment)
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
                        binding.dashboardScrollview.requestDisallowInterceptTouchEvent(true)
                        false
                    }
                    ACTION_UP -> {
                        binding.dashboardScrollview.requestDisallowInterceptTouchEvent(false)
                        true
                    }
                    ACTION_MOVE -> {
                        binding.dashboardScrollview.requestDisallowInterceptTouchEvent(true)
                        false
                    }
                    else -> true
                }
            }
        })

        viewModel.initialize()
    }

    private fun processMapCircles(list: List<MapCircle>?) {
        val latLongs = list?.map { WeightedLatLng(it.center, it.weight) }
        latLongs?.let {
            if(it.isNotEmpty()) {
                val provider = HeatmapTileProvider.Builder()
                    .weightedData(it)
                    .radius(50)
                    .build()
                map?.addTileOverlay(TileOverlayOptions().tileProvider(provider))
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

    override fun onCountryMoreClicked(index: Int) {
        val action = DashboardFragmentDirections.actionDashboardToSearch(index)
        findNavController().navigate(action)
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
        binding.dashboardGlobalTotalsValue.textColor = ContextCompat.getColor(requireContext(), chartData.color)
        binding.dashboardGlobalTotalsSparkview.lineColor = ContextCompat.getColor(requireContext(), chartData.color)
    }

    private fun onScrubbed(value: Any?) {
        if (value is ChartDataValue) {
            binding.dashboardGlobalTotalsValue.setText(value.count.toString(), true)
            binding.dashboardGlobalTotalsDate.text = value.date
        }
    }
}