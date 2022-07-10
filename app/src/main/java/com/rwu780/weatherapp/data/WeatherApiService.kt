package com.rwu780.weatherapp.data

import com.rwu780.weatherapp.BuildConfig
import com.rwu780.weatherapp.data.model.ResponseCity
import com.rwu780.weatherapp.data.model.ResponseWeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiService {

    @GET("/search.json")
    suspend fun searchCityByKeyword(
        @Query("key") api_key : String = BuildConfig.WEATHER_API_KEY,
        @Query("q") keyword : String
    ) : List<ResponseCity>

    @GET("/forecast.json")
    suspend fun getForecastByCityName(
        @Query("key") api_key: String = BuildConfig.WEATHER_API_KEY,
        @Query("q") keyword: String,
        @Query("days") numberOfForeastDays : Int = 7,
    ) : ResponseWeatherForecast

    companion object {
        const val BASE_URL = "http://api.weatherapi.com/v1"
    }
}