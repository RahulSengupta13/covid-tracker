package com.rahulsengupta.architecture.android.flows.search

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulsengupta.core.di.ICoroutinesDispatcher
import com.rahulsengupta.core.model.CountryItem
import com.rahulsengupta.core.repository.ICoreRepository
import com.rahulsengupta.core.repository.LoadingState
import com.rahulsengupta.core.usecase.IGetCountryItemsListUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val countryItemsListUseCase: IGetCountryItemsListUseCase,
    private val coroutineDispatcher: ICoroutinesDispatcher,
    private val coreRepository: ICoreRepository
) : ViewModel() {

    val searchCountries = ObservableField<List<CountryItem>>()
    val scrollRecyclerViewToPosition = ObservableField<Int>()

    var scrollToIndex = 0
    var hasScrolledToIndex = false

    init {
        initialize()
    }

    private fun initialize() {
        viewModelScope.launch(coroutineDispatcher.IO) {
            countryItemsListUseCase.flow.collect {
                searchCountries.set(it)
                delay(100)
                if(!hasScrolledToIndex) {
                    onRecyclerItemSelected(scrollToIndex)
                    hasScrolledToIndex = true
                }
            }
        }
    }

    fun onRecyclerItemSelected(position: Int) {
        scrollRecyclerViewToPosition.set(position)
    }

    fun onSearchTextChanged(searchText: String) {
        /*viewModelScope.launch(coroutineDispatcher.IO) {
            if(searchText.isNotEmpty()) {
                globalCountryUseCase.flow.collect { list ->
                    list?.filter { it.country.contains(searchText, true) }
                        ?.sortedBy { searchText }
                        ?.map { SearchCountryItem(it.country, it.countryInfo.flag ?: "") }
                        ?.also { searchCountries.set(it) }
                }
            } else {
                initialize()
            }
        }*/
    }

    fun refresh() {
        coreRepository.initialize()
    }

    fun setIndexToScrollTo(index: Int) {
        scrollToIndex = index
    }
}