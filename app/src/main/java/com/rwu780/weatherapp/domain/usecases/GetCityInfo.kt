package com.rwu780.weatherapp.domain.usecases

import com.rwu780.weatherapp.domain.WeatherRepository
import com.rwu780.weatherapp.domain.model.City
import com.rwu780.weatherapp.util.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCityInfo @Inject constructor(
    private val repository: WeatherRepository
){

    operator fun invoke(word: String) : Flow<ResultState<List<City>>> {
        if (word.isBlank()){
            return flow {  }
        }

        return repository.searchCityByKeyWord(word.replace(" ", ""))
    }
}