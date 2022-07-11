package com.rwu780.weatherapp.domain.model

data class HourlyForecast(
    val hour : String,
    val temperature : String,
    val status_icon : String
)