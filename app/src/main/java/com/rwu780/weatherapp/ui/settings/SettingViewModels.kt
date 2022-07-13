package com.rwu780.weatherapp.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rwu780.weatherapp.domain.Temperature_Units
import com.rwu780.weatherapp.domain.usecases.LoadTemperatureUnits
import com.rwu780.weatherapp.domain.usecases.SavedTemperatureUnits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModels @Inject constructor(
    private val loadTemperatureUnits: LoadTemperatureUnits,
    private val savedTemperatureUnits: SavedTemperatureUnits
) : ViewModel() {

    lateinit var temperatureUnits : Temperature_Units

    init {
        getUnits()
    }

    fun savedUnits(unit: String){
        viewModelScope.launch {
            savedTemperatureUnits(unit)
        }
    }

    fun getUnits() {
        viewModelScope.launch {
            temperatureUnits = loadTemperatureUnits()
        }
    }
}