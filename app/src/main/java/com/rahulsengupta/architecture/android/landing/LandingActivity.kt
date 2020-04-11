package com.rahulsengupta.architecture.android.landing

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.rahulsengupta.architecture.R
import com.rahulsengupta.architecture.databinding.ActivityLandingBinding
import com.rahulsengupta.core.base.InjectableActivity
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : InjectableActivity() {

    companion object {
        private val TOP_LEVEL_DESTINATIONS = setOf(
            R.id.dashboard,
            R.id.settings,
            R.id.live_reports
        )
    }

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var currentNavId = R.id.splashFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityLandingBinding>(
            this,
            R.layout.activity_landing
        )
        binding.lifecycleOwner = this

        navController = findNavController(R.id.nav_fragment)
        appBarConfiguration = AppBarConfiguration(TOP_LEVEL_DESTINATIONS)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentNavId = destination.id
            val isTopLevelDestination = TOP_LEVEL_DESTINATIONS.contains(destination.id)
            bottomNavigationView.visibility = if (isTopLevelDestination) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    override fun onSupportNavigateUp() = navController.navigateUp() || super.onSupportNavigateUp()
}