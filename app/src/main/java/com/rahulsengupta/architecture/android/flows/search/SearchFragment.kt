package com.rahulsengupta.architecture.android.flows.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahulsengupta.architecture.R
import com.rahulsengupta.core.base.InjectableFragment

class SearchFragment : InjectableFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

}