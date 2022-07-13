package com.rwu780.weatherapp.data


import com.rwu780.weatherapp.data.model.DailyForecastDto
import com.rwu780.weatherapp.data.model.WeatherForecastDto
import com.rwu780.weatherapp.domain.model.CurrentWeather
import java.text.SimpleDateFormat
import java.util.*


fun WeatherForecastDto.toCurrentWeather() : CurrentWeather {

    var currentDate = getCurrentDate()

    var forecast : Pair<List<DailyForecastDto>, List<DailyForecastDto>> = this.forecastDto.dailyForecastDto.partition { it.date == currentDate }


    if (forecast.first.isEmpty()){
        currentDate = getNextDate(currentDate)
        forecast = this.forecastDto.dailyForecastDto.partition { date ->
            date.date == currentDate

        }
    }

    val currentDay = forecast.first
    val upcomingDay = forecast.second

    val hourlyForecast = currentDay[0].hourlyTemperatureDto.map {
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


fun getNextDate(currentDate: String): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd")

    val c: Calendar = Calendar.getInstance()
    c.time = formatter.parse(currentDate)
    c.add(Calendar.DATE, 1)

    return formatter.format(c.time)

}

fun parseStringToDate(input: String): Date {

    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
    return formatter.parse(input)
}

