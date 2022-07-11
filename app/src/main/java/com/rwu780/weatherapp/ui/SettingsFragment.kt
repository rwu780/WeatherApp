package com.rwu780.weatherapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.rwu780.weatherapp.R


class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference, rootKey)
    }

    override fun onResume() {
        (activity as AppCompatActivity).supportActionBar?.show()
        super.onResume()
    }

    override fun onPause() {
        (activity as AppCompatActivity).supportActionBar?.hide()
        super.onPause()
    }

}