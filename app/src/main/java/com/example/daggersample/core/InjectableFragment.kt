package com.example.daggersample.core

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.daggersample.core.di.Injectable
import javax.inject.Inject

open class InjectableFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
}