package com.rwu780.weatherapp.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.rwu780.weatherapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    private val viewModel: SettingViewModels by viewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference, rootKey)

        (activity as AppCompatActivity).title = "Settings"
        initValues()
    }



    override fun onResume() {
        (activity as AppCompatActivity).title = "Settings"
        (activity as AppCompatActivity).supportActionBar?.show()
        super.onResume()
    }

    override fun onPause() {
        (activity as AppCompatActivity).supportActionBar?.hide()
        super.onPause()
    }

    private fun initValues() {
        findPreference<ListPreference>("units_select")?.value = viewModel.temperatureUnits.toString()

        findPreference<ListPreference>("units_select")?.summaryProvider =
            Preference.SummaryProvider<ListPreference> { preference ->
                val text = preference.value

                viewModel.savedUnits(text)
                text
            }
    }
}