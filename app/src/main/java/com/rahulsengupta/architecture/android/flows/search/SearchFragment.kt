package com.rahulsengupta.architecture.android.flows.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rahulsengupta.architecture.databinding.FragmentSearchBinding
import com.rahulsengupta.core.base.InjectableFragment
import com.rahulsengupta.core.base.ScalingLayoutManager
import com.rahulsengupta.core.customview.OffsetItemDecoration
import timber.log.Timber

class SearchFragment : InjectableFragment(), ScalingLayoutManager.OnItemSelectedListener {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchRecyclerView.run {
            layoutManager = ScalingLayoutManager(requireContext(), this@SearchFragment)
            adapter = SearchCountriesAdapter()
            addItemDecoration(OffsetItemDecoration(requireContext()))
        }
    }

    override fun onItemSelected(layoutPosition: Int) {
        Timber.d("SearchFragment selected: $layoutPosition")
    }
}