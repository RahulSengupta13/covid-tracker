package com.example.architecture.android.core

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.architecture.android.core.di.Injectable
import javax.inject.Inject

open class InjectableFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
}