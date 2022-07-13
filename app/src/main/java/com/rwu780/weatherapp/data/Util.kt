package com.rwu780.weatherapp.data


import com.rwu780.weatherapp.data.model.WeatherForecastDto
import com.rwu780.weatherapp.domain.model.CurrentWeather
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


fun WeatherForecastDto.toCurrentWeather() : CurrentWeather {

    val currentDate = getCurrentDate()

    val (currentToday, upcomingDay) = this.forecastDto.dailyForecastDto.partition { it.date == currentDate }

    val hourlyForecast = currentToday[0].hourlyTemperatureDto.map {
        it.toHourlyForecast()
    }.filter {
        Date() < parseStringToDate(it.date)
    }

    val futureForecast = upcomingDay.map {
        it.toDailyForecast()
    }

    val city = this.locationDto.toCity()

    return CurrentWeather(
        this.currentWeatherDto.tempC.toString(),
        this.currentWeatherDto.conditionDto.text,
        city,
        hourlyForecast,
        futureForecast
    )

}

fun getCurrentDate(): String {

    val formatter = SimpleDateFormat("yyyy-MM-dd")

    return formatter.format(Date())

}

fun parseStringToDate(input: String): Date {



    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")

    return formatter.parse(input)
}

