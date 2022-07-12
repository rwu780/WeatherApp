package com.rwu780.weatherapp.data

import com.rwu780.weatherapp.data.model.CityDto
import com.rwu780.weatherapp.data.model.WeatherForecastDto
import com.rwu780.weatherapp.domain.WeatherRepository
import com.rwu780.weatherapp.domain.model.City
import com.rwu780.weatherapp.domain.model.CurrentWeather
import com.rwu780.weatherapp.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

private const val TAG = "WeatherRepositoryImpl"
class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi : WeatherApi
) : WeatherRepository {


    override fun searchCityByKeyWord(keyword: String): Flow<ResultState<List<City>>> = flow {
        emit(ResultState.Loading())

        try {
            val findMatchedCities : List<CityDto> = weatherApi.searchCityByKeyword(keyword = keyword)

            if (findMatchedCities.isEmpty()){
                emit(ResultState.Error("No city matched keyword"))
            }

            val matched_cities : List<City> = findMatchedCities.let { matchedCities ->
                matchedCities.map {
                    it.toCity()
                }
            }

            emit(ResultState.Success(matched_cities))

        } catch (e: HttpException){

            emit(ResultState.Error("Unable to fetch cities, please check your internet connections"))
        }
    }

    override fun getForecastByCityName(cityname: String): Flow<ResultState<CurrentWeather>> = flow {

        emit(ResultState.Loading())

        try {
            val fetchWeather : WeatherForecastDto = weatherApi.getForecastByCityName(keyword = cityname)

            val weatherForecast: CurrentWeather = fetchWeather.toCurrentWeather()

            emit(ResultState.Success(weatherForecast))

        } catch (e: Exception){
            emit(ResultState.Error("Unable to fetch data, please check your internet connections"))
        }
    }.flowOn(Dispatchers.IO)
}