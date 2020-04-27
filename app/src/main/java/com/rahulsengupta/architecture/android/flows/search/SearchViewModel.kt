package com.rahulsengupta.architecture.android.flows.search

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulsengupta.core.di.ICoroutinesDispatcher
import com.rahulsengupta.persistence.enitity.GlobalCountryEntity
import com.rahulsengupta.persistence.usecase.IGetGlobalCountryUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val globalCountryUseCase: IGetGlobalCountryUseCase,
    private val coroutineDispatcher: ICoroutinesDispatcher
) : ViewModel() {

    val searchCountries = ObservableField<List<SearchCountryItem>>()
    val scrollToPosition = ObservableField<Int>()

    init {
        initialize()
    }

    fun initialize() {
        viewModelScope.launch(coroutineDispatcher.IO) {
            globalCountryUseCase.flow.collect {
                initializeSearchCountries(it)
            }
        }
    }

    private fun initializeSearchCountries(list: List<GlobalCountryEntity>?) {
        list?.let { countryEntities ->
            val countries = countryEntities
                .sortedByDescending { it.cases }
                .map { SearchCountryItem(it.country, it.countryInfo.flag ?: "") }
            searchCountries.set(countries)
            scrollToPosition.set(0)
        }
    }
}