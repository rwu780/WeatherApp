package com.rwu780.weatherapp.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class ForecastDto(
    @Json(name = "forecastday")
    val dailyForecastDto: List<DailyForecastDto>
)