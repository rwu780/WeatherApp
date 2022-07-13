package com.rwu780.weatherapp.domain.model

import com.rwu780.weatherapp.domain.Temperature_Units

data class HourlyForecast(
    val date : String,
    val temperature_c : String,
    val temperature_f : String,
    val status_icon : String
) {
    fun getHour(): String {
        return try{
            date.split(' ')[1].split(':')[0]
        } catch (e: Exception){
            ""
        }
    }

    fun getTemperature(units: Temperature_Units) : String {
        return if (units == Temperature_Units.Celsius) temperature_c else temperature_f
    }
}