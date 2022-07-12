package com.rwu780.weatherapp.domain.model

data class CurrentWeather(
    val current_temperature: String,
    val current_status : String,
    val city: City,
    val hourlyForecast : List<HourlyForecast>,
    val dailyForecast: List<DailyForecast>
) {
}