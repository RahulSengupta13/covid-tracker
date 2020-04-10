package com.rahulsengupta.architecture.android.landing

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.rahulsengupta.architecture.R
import com.rahulsengupta.architecture.databinding.ActivityLandingBinding
import com.rahulsengupta.core.base.InjectableActivity

class LandingActivity : InjectableActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityLandingBinding>(
            this,
            R.layout.activity_landing
        )
        binding.lifecycleOwner = this

        navController = findNavController(R.id.nav_fragment)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

    override fun onSupportNavigateUp() = navController.navigateUp() || super.onSupportNavigateUp()
}