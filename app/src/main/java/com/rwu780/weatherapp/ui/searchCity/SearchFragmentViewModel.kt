package com.rwu780.weatherapp.ui.searchCity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rwu780.weatherapp.domain.model.City
import com.rwu780.weatherapp.domain.usecases.GetCityInfo
import com.rwu780.weatherapp.domain.usecases.StoredLocation
import com.rwu780.weatherapp.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val getCityInfo: GetCityInfo,
    private val storedLocation: StoredLocation
) : ViewModel() {

    private val _searchState = MutableStateFlow<SearchFragmentUiState>(SearchFragmentUiState.Empty)
    val searchState: StateFlow<SearchFragmentUiState> = _searchState

    private var job: Job? = null

    fun searchLocationWithKeyword(keyword: String) {

        job?.cancel()

        job = viewModelScope.launch {
            _searchState.value = SearchFragmentUiState.Loading

            getCityInfo(keyword)
                .catch {
                    _searchState.value = SearchFragmentUiState.Error(it.message ?: "Unknown Error")
                }
                .collect { result ->
                    when (result){
                        is ResultState.Success -> {
                            result.data?.let {
                                _searchState.value = SearchFragmentUiState.Success(it)
                            } ?: kotlin.run {
                                _searchState.value = SearchFragmentUiState.Error("Unable to find a match")
                            }
                        }
                        is ResultState.Error -> {
                            _searchState.value = SearchFragmentUiState.Error(result.message ?: "Unknown Error")
                        }
                        else -> Unit
                    }
                }
        }
    }

    fun saveLocation(city: City) {
        runBlocking {
            val cityName = "${city.name},${city.region}"
            storedLocation(cityName)
        }
    }
}