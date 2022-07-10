package com.rwu780.weatherapp.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DailyForecast(
    @Json(name = "astro")
    val astro: Astro?,
    @Json(name = "date")
    val date: String?,
    @Json(name = "date_epoch")
    val dateEpoch: Int?,
    @Json(name = "day")
    val dailyTemperature: DailyTemperature?,
    @Json(name = "hour")
    val hourlyTemperature: List<HourlyTemperature>?
)