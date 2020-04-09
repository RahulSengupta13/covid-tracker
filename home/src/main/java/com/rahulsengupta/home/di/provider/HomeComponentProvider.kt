package com.rahulsengupta.home.di.provider

import com.rahulsengupta.home.di.HomeComponent

interface HomeComponentProvider {
    fun getHomeComponent(): HomeComponent
}