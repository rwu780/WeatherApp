package com.rwu780.weatherapp.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseWeatherForecast(
    @Json(name = "current")
    val currentWeather: CurrentWeather?,
    @Json(name = "forecast")
    val forecast: Forecast?,
    @Json(name = "location")
    val location: Location?
)