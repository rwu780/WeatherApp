package com.rwu780.weatherapp.domain.model

import com.rwu780.weatherapp.domain.Temperature_Units

data class CurrentWeather(
    val current_temperature_c: String,
    val current_temperature_f: String,
    val current_status : String,
    val city: City,
    val hourlyForecast : List<HourlyForecast>,
    val dailyForecast: List<DailyForecast>
) {

    fun getCurrentTemperature(units: Temperature_Units) : String {
        return if (units == Temperature_Units.Celsius) current_temperature_c else current_temperature_f
    }
}