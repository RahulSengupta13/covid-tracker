package com.example.home.di.provider

import com.example.home.di.HomeComponent

interface HomeComponentProvider {
    fun getHomeComponent(): HomeComponent
}