package com.rwu780.weatherapp.data.model



import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class WeatherForecastDto(
    @Json(name = "current")
    val currentWeatherDto: CurrentWeatherDto,
    @Json(name = "forecast")
    val forecastDto: ForecastDto,
    @Json(name = "location")
    val locationDto: LocationDto
) {
}