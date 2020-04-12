package com.rahulsengupta.architecture.android.flows.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.rahulsengupta.architecture.R
import com.rahulsengupta.architecture.android.flows.splash.model.ViewEffect.NavigateToDashboard
import com.rahulsengupta.core.base.InjectableFragment

class SplashFragment : InjectableFragment() {

    private val viewModel: SplashViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewEffect.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NavigateToDashboard -> navigateToDashboard()
            }
        })
    }

    private fun navigateToDashboard() {
        val action = SplashFragmentDirections.actionSplashFragmentToDashboard()
        findNavController().navigate(action)
    }
}