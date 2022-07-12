package com.rwu780.weatherapp.ui.dashboard

import com.rwu780.weatherapp.R

fun getIconBasedOnWeatherStatus(text: String) =
    when (text) {
        "Sunny" -> R.drawable.sunny
        "Clear" -> R.drawable.clear
        "Partly cloudy" -> R.drawable.cloudy_clear
        "Cloudy" -> R.drawable.cloudy
        "Overcast" -> R.drawable.cloudy
        "Mist" -> R.drawable.fog
        "Patchy rain possible" -> R.drawable.rain_sun
        "Patchy snow possible" -> R.drawable.snow
        "Patchy sleet possible" -> R.drawable.sleet
        "Patchy freezing drizzle possible" -> R.drawable.drizzle
        "Thundery outbreaks possible" -> R.drawable.scattered_thunderstorm
        "Blowing snow" -> R.drawable.blowing_snow
        "Blizzard" -> R.drawable.blizzard
        "Fog" -> R.drawable.fog
        "Freezing fog" -> R.drawable.fog
        "Patchy light drizzle" -> R.drawable.drizzle
        "Light drizzle" -> R.drawable.drizzle
        "Freezing drizzle" -> R.drawable.drizzle
        "Heavy freezing drizzle" -> R.drawable.drizzle
        "Patchy light rain" -> R.drawable.rain_sun
        "Light rain" -> R.drawable.rain
        "Moderate rain at times" -> R.drawable.rain_sun
        "Moderate rain" -> R.drawable.rain
        "Heavy rain at times" -> R.drawable.scattered_shower
        "Heavy rain" -> R.drawable.heavy_rain
        "Light freezing rain" -> R.drawable.sleet
        "Moderate or heavy freezing rain" -> R.drawable.sleet
        "Light sleet" -> R.drawable.sleet
        "Moderate or heavy sleet" -> R.drawable.sleet
        "Patchy light snow" -> R.drawable.snow
        "Light snow" -> R.drawable.snow
        "Patchy moderate snow" -> R.drawable.snow
        "Moderate snow" -> R.drawable.snow
        "Patchy heavy snow" -> R.drawable.snow
        "Heavy snow" -> R.drawable.snow
        "Ice pellets" -> R.drawable.hail
        "Light rain shower" -> R.drawable.rain
        "Moderate or heavy rain shower" -> R.drawable.heavy_rain
        "Torrential rain shower" -> R.drawable.heavy_rain
        "Light sleet showers" -> R.drawable.sleet
        "Moderate or heavy sleet showers" -> R.drawable.sleet
        "Light snow showers" -> R.drawable.snow
        "Moderate or heavy snow showers" -> R.drawable.snow
        "Light showers of ice pellets" -> R.drawable.hail
        "Moderate or heavy showers of ice pellets" -> R.drawable.hail
        "Patchy light rain with thunder" -> R.drawable.rain_thunderstorm
        "Moderate or heavy rain with thunder" -> R.drawable.sever_thunderstorm
        "Patchy light snow with thunder" -> R.drawable.sever_thunderstorm
        "Moderate or heavy snow with thunder" -> R.drawable.sever_thunderstorm
        else -> R.drawable.circle
    }