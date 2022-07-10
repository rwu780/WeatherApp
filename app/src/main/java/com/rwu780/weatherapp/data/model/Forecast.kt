package com.rwu780.weatherapp.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Forecast(
    @Json(name = "forecastday")
    val dailyForecast: List<DailyForecast>?
)