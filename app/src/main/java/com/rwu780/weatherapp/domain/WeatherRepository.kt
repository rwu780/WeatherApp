package com.rwu780.weatherapp.domain


import com.rwu780.weatherapp.domain.model.City
import com.rwu780.weatherapp.domain.model.CurrentWeather
import com.rwu780.weatherapp.util.ResultState
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun searchCityByKeyWord(keyword: String) : Flow<ResultState<List<City>>>

    fun getForecastByCityName(cityname: String) : Flow<ResultState<CurrentWeather>>

}