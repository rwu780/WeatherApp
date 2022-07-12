package com.rwu780.weatherapp.data

import com.rwu780.weatherapp.BuildConfig
import com.rwu780.weatherapp.data.model.CityDto
import com.rwu780.weatherapp.data.model.WeatherForecastDto
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET("search.json")
    suspend fun searchCityByKeyword(
        @Query("key") api_key : String = BuildConfig.WEATHER_API_KEY,
        @Query("q") keyword : String
    ) : List<CityDto>

    @GET("forecast.json")
    suspend fun getForecastByCityName(
        @Query("key") api_key: String = BuildConfig.WEATHER_API_KEY,
        @Query("q") keyword: String,
        @Query("days") numberOfForeastDays : Int = 7,
    ) : WeatherForecastDto

    companion object {
        const val BASE_URL = "https://api.weatherapi.com/v1/"
    }
}