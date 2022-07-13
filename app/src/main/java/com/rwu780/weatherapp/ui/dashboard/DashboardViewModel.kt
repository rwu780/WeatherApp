package com.rwu780.weatherapp.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.rwu780.weatherapp.domain.usecases.GetWeatherByCityName
import com.rwu780.weatherapp.domain.usecases.RetrieveSavedCityName
import com.rwu780.weatherapp.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "DashboardViewModel"

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getWeatherByCityName: GetWeatherByCityName,
    private val retrieveSavedCityName: RetrieveSavedCityName
) : ViewModel() {

    private val _dashboardState = MutableStateFlow<DashboardUiState>(DashboardUiState.Empty)
    val dashboardState: StateFlow<DashboardUiState> = _dashboardState

    private val _cityName = MutableLiveData("")
    val cityName : LiveData<String> = _cityName

    init {
        loadCityName()
    }

    private fun loadCityName() {
        viewModelScope.launch {
            retrieveSavedCityName().collect {
                if (it.isNotBlank()){
                    _cityName.value = it
                } else {
                    _cityName.value = fetchFromLocation()
                }
            }
        }
    }

    private fun fetchFromLocation(): String {

        return "Vancouver,BC"
    }

    fun fetchWeather(keyword: String) {

        viewModelScope.launch {
            _dashboardState.value = DashboardUiState.Loading

            getWeatherByCityName(keyword)
                .catch {
                    Log.d(TAG, "fetchWeather: " + it.message)
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
}