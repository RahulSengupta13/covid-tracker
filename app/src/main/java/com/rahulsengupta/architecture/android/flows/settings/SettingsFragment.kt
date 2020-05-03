package com.rahulsengupta.architecture.android.flows.settings

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.rahulsengupta.architecture.R
import com.rahulsengupta.core.di.Injectable
import javax.inject.Inject

class SettingsFragment : PreferenceFragmentCompat(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var preference: SharedPreferences

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preference_fragment, rootKey)
        preference = PreferenceManager.getDefaultSharedPreferences(requireContext())

        val preference = findPreference<ListPreference>("theme")
        preference?.setOnPreferenceChangeListener { preference, newValue ->
            val themeValues = resources.getStringArray(R.array.theme_values)
            val value = (newValue as? String)
            when (themeValues.indexOf(value)) {
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
            true
        }

    }
}