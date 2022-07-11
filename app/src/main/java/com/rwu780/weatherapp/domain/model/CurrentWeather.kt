package com.rwu780.weatherapp.domain.model

data class CurrentWeather(
    val current_temperature: String,
    val current_status_icon : String,
    val hourlyForecast : List<HourlyForecast>,
    val dailyForecast: List<DailyForecast>
) {
}