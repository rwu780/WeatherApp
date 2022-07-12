package com.rwu780.weatherapp.ui.dashboard

import com.rwu780.weatherapp.domain.model.CurrentWeather

sealed class DashboardUiState {
    object Loading: DashboardUiState()
    data class Success(val data: CurrentWeather) : DashboardUiState()
    data class Error(val errorMsg: String) : DashboardUiState()
    object Empty : DashboardUiState()
}