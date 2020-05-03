package com.rahulsengupta.architecture.android.flows.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.iammert.library.ui.multisearchviewlib.MultiSearchView
import com.rahulsengupta.architecture.R
import com.rahulsengupta.architecture.databinding.FragmentSearchBinding
import com.rahulsengupta.core.base.InjectableFragment
import com.rahulsengupta.core.base.ScaleTransformer
import com.rahulsengupta.core.base.ScalingLayoutManager
import com.rahulsengupta.core.customview.OffsetItemDecoration

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

        binding.searchViewpager.run {
            (getChildAt(0) as? RecyclerView)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            adapter = SearchCountryCardAdapter()
            isUserInputEnabled = false
            setPageTransformer(
                CompositePageTransformer().apply {
                    addTransformer(MarginPageTransformer(resources.getDimensionPixelOffset(R.dimen.view_pager_page_margin_small)))
                    addTransformer(ScaleTransformer())
                }
            )
        }

        binding.multiSearchView.setSearchViewListener(object : MultiSearchView.MultiSearchViewListener {
            override fun onItemSelected(index: Int, s: CharSequence) = viewModel.onSearchTextChanged(s.toString())
            override fun onSearchComplete(index: Int, s: CharSequence) = viewModel.initialize()
            override fun onSearchItemRemoved(index: Int) = viewModel.initialize()
            override fun onTextChanged(index: Int, s: CharSequence) = viewModel.onSearchTextChanged(s.toString())
        })
    }

    override fun onItemSelected(layoutPosition: Int) = viewModel.onRecyclerItemSelected(layoutPosition)
}