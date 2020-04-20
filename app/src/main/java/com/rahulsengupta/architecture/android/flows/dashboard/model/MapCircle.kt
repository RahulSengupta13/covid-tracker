package com.rahulsengupta.architecture.android.flows.dashboard.model

import com.google.android.gms.maps.model.LatLng

data class MapCircle(
    val center: LatLng,
    val radius: Double,
    val cases: Int,
    val country: String,
    val weight: Double
)