package com.rahulsengupta.core.usecase

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import javax.inject.Inject

interface IThemeHelperUseCase {
    fun setTheme(themes: Array<String>, selectedTheme: String?)
}

class ThemeHelperUseCase @Inject constructor() : IThemeHelperUseCase {

    override fun setTheme(themes: Array<String>, selectedTheme: String?) {
        when (themes.indexOf(selectedTheme)) {
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            2 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
            else -> Unit
        }
    }
}