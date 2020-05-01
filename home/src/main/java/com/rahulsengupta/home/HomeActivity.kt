package com.rahulsengupta.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.rahulsengupta.core.di.ViewModelFactory
import com.rahulsengupta.home.di.provider.HomeComponentProvider
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: HomeActivityViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as HomeComponentProvider)
            .getHomeComponent()
            .inject(this)

        setContentView(R.layout.activity_home)

        viewModel.uiData.observe(this, Observer {
            ticker.text = it.toString()
        })
    }
}