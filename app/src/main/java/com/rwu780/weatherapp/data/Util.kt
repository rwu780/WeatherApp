package com.rwu780.weatherapp.data


import com.rwu780.weatherapp.data.model.WeatherForecastDto
import com.rwu780.weatherapp.domain.model.CurrentWeather
import java.text.SimpleDateFormat
import java.util.*


fun WeatherForecastDto.toCurrentWeather() : CurrentWeather {

    val currentDate = getCurrentDate()

    val (currentToday, upcomingDay) = this.forecastDto.dailyForecastDto.partition { it.date == currentDate }

    val hourlyForecast = currentToday[0].hourlyTemperatureDto.map {
        it.toHourlyForecast()
    }

    val futureForecast = upcomingDay.map {
        it.toDailyForecast()
    }

    return CurrentWeather(
        this.currentWeatherDto.tempC.toString(),
        this.currentWeatherDto.conditionDto.icon,
        hourlyForecast,
        futureForecast
    )

}

fun getCurrentDate(): String {

    val formatter = SimpleDateFormat("yyyy-MM-dd")

    return formatter.format(Date())

}