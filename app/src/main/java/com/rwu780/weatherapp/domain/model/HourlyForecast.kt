package com.rwu780.weatherapp.domain.model

data class HourlyForecast(
    val date : String,
    val temperature : String,
    val status_icon : String
) {
    fun getHour(): String {
        return try{
            date.split(' ')[1].split(':')[0]
        } catch (e: Exception){
            ""
        }
    }
}