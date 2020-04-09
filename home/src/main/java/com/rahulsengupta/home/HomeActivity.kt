package com.rahulsengupta.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.rahulsengupta.core.di.ViewModelFactory
import com.rahulsengupta.home.di.provider.HomeComponentProvider
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
            Toast.makeText(this, "$it", Toast.LENGTH_LONG).show()
        })
    }
}