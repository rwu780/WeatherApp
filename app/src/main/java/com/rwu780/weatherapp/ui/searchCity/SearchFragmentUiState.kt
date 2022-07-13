package com.rwu780.weatherapp.ui.searchCity

import com.rwu780.weatherapp.domain.model.City

sealed class SearchFragmentUiState {
    object Loading: SearchFragmentUiState()
    data class Success(val data: List<City>): SearchFragmentUiState()
    data class Error(val errorMsg: String): SearchFragmentUiState()
    object Empty: SearchFragmentUiState()
}
