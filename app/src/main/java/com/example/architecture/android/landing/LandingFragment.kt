package com.example.architecture.android.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.architecture.R
import com.example.architecture.android.core.InjectableFragment
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

        viewModel.posts.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it.size.toString(), Toast.LENGTH_LONG).show()
        })

        viewModel.ticker.observe(viewLifecycleOwner, Observer {
            ticker.text = "${it.price} ; ${it.time}"
        })
    }
}