package com.rwu780.weatherapp.domain.model

import com.rwu780.weatherapp.domain.Temperature_Units

data class DailyForecast(
    val date: String,
    val day_high_c : String,
    val day_low_c : String,
    val day_high_f : String,
    val day_low_f : String,
    val status_icon : String
) {
    fun getDayHigh(units: Temperature_Units) : String {
        return if (units == Temperature_Units.Celsius) day_high_c else day_high_f

    }

    fun getDayLow(units: Temperature_Units) : String {
        return if (units == Temperature_Units.Celsius) day_low_c else day_low_f
    }
}

