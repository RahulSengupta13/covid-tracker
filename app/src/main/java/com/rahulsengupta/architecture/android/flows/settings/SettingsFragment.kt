package com.rahulsengupta.architecture.android.flows.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahulsengupta.architecture.R
import com.rahulsengupta.core.base.InjectableFragment

class SettingsFragment : InjectableFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

}