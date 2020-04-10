package com.rahulsengupta.architecture.android.flows.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rahulsengupta.architecture.R
import com.rahulsengupta.core.base.InjectableFragment
import com.rahulsengupta.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : InjectableFragment() {

    private val viewModel: DashboardViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiData.observe(viewLifecycleOwner, Observer {
            ticker.text = it.toString()
        })

        ticker.setOnClickListener { startActivity(Intent(context, HomeActivity::class.java)) }
    }
}