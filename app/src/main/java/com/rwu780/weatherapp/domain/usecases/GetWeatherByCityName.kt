package com.rwu780.weatherapp.domain.usecases

import com.rwu780.weatherapp.domain.WeatherRepository
import com.rwu780.weatherapp.domain.model.CurrentWeather
import com.rwu780.weatherapp.util.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeatherByCityName @Inject constructor(
    private val repository: WeatherRepository
) {

    operator fun invoke(cityName: String) : Flow<ResultState<CurrentWeather>> {

        if (cityName.isBlank()){
            return flow { }
        }

        return repository.getForecastByCityName(cityName)
    }
}