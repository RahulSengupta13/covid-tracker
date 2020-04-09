package com.rahulsengupta.architecture.android.landing

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rahulsengupta.architecture.R
import com.rahulsengupta.core.base.InjectableFragment
import com.rahulsengupta.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_landing.*

class LandingFragment : InjectableFragment() {

    private val viewModel: LandingViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_landing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ticker.setOnClickListener { startActivity(Intent(context, HomeActivity::class.java)) }

        viewModel.initialize()
    }
}