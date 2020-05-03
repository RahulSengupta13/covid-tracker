package com.rahulsengupta.architecture.android.flows.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.rahulsengupta.architecture.R
import com.rahulsengupta.core.di.Injectable
import com.rahulsengupta.core.usecase.ThemeHelperUseCase
import javax.inject.Inject

class SettingsFragment : PreferenceFragmentCompat(), Injectable {

    @Inject
    lateinit var themeHelper: ThemeHelperUseCase

    lateinit var preference: SharedPreferences

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preference_fragment, rootKey)
        preference = PreferenceManager.getDefaultSharedPreferences(requireContext())

        val preference = findPreference<ListPreference>("theme")
        preference?.setOnPreferenceChangeListener { _, newValue ->
            val themeValues = resources.getStringArray(R.array.theme_values)
            val value = (newValue as? String)
            themeHelper.setTheme(themeValues, value)
            true
        }

    }
}