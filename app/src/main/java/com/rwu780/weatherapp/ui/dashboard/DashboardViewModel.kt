package com.rwu780.weatherapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rwu780.weatherapp.domain.Temperature_Units
import com.rwu780.weatherapp.domain.usecases.*
import com.rwu780.weatherapp.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getWeatherByCityName: GetWeatherByCityName,
    private val retrieveSavedCityName: RetrieveSavedCityName,
    private val getCurrentLocation: GetCurrentLocation,
    private val storedLocation: StoredLocation,
    private val loadTemperatureUnits: LoadTemperatureUnits
) : ViewModel() {

    private val _dashboardState = MutableStateFlow<DashboardUiState>(DashboardUiState.Empty)
    val dashboardState: StateFlow<DashboardUiState> = _dashboardState

    private val _cityName = MutableLiveData("")
    val cityName : LiveData<String> = _cityName

    lateinit var temperatureUnits : Temperature_Units

    fun loadCityName() {
        viewModelScope.launch {
            val name = retrieveSavedCityName()

            if (name.isBlank()){
                fetchCurrentLocation()
            } else {
                _cityName.value = name
            }
        }
    }

    fun fetchCurrentLocation() {
        viewModelScope.launch {
            getCurrentLocation()?.let { location ->
                val newLocation="${location.latitude},${location.longitude}"

                saveLocation(newLocation)
                _cityName.value=newLocation
            }
        }

    }

    private fun saveLocation(latlong: String) {
        runBlocking {
            storedLocation(latlong)
        }
    }

    fun fetchWeather(keyword: String) {
        if (keyword.isBlank())
            return

        viewModelScope.launch {
            _dashboardState.value = DashboardUiState.Loading

            getWeatherByCityName(keyword)
                .catch {
                    _dashboardState.value = DashboardUiState.Error(
                        it.message ?: "Unknown Error"
                    )
                }
                .collect { result ->
                    when (result) {
                        is ResultState.Success -> {
                            result.data?.let { data ->
                                _dashboardState.value = DashboardUiState.Success(data)
                            } ?: kotlin.run {
                                _dashboardState.value = DashboardUiState.Error("No data available")
                            }
                        }

                        is ResultState.Error -> {
                            _dashboardState.value =
                                DashboardUiState.Error(
                                    result.message ?: "Unknown Error"
                                )
                        }
                        else -> Unit
                    }
                }
        }
    }

    fun loadUnits() {
        viewModelScope.launch {
            temperatureUnits = loadTemperatureUnits()

        }
    }
}