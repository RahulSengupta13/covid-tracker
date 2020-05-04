package com.rahulsengupta.architecture.android.flows.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.rahulsengupta.architecture.R
import com.rahulsengupta.architecture.databinding.FragmentSearchBinding
import com.rahulsengupta.core.base.InjectableFragment
import com.rahulsengupta.core.base.ScaleTransformer
import com.rahulsengupta.core.base.ScalingLayoutManager
import com.rahulsengupta.core.customview.OffsetItemDecoration
import kotlinx.android.synthetic.main.fragment_search.*

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

        (requireActivity() as AppCompatActivity).setSupportActionBar(search_toolbar)

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

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> findNavController().popBackStack()
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemSelected(layoutPosition: Int) = viewModel.onRecyclerItemSelected(layoutPosition)
}