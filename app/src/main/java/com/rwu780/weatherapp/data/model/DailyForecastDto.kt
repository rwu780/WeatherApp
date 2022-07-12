package com.rwu780.weatherapp.data.model


import com.rwu780.weatherapp.domain.model.DailyForecast
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class DailyForecastDto(
    @Json(name = "astro")
    val astroDto: AstroDto?,
    @Json(name = "date")
    val date: String,
    @Json(name = "date_epoch")
    val dateEpoch: Int?,
    @Json(name = "day")
    val dailyTemperatureDto: DailyTemperatureDto,
    @Json(name = "hour")
    val hourlyTemperatureDto: List<HourlyTemperatureDto>
) {
    fun toDailyForecast() : DailyForecast {
        return DailyForecast(
            date,
            dailyTemperatureDto.maxtempC.toString(),
            dailyTemperatureDto.mintempC.toString(),
            dailyTemperatureDto.conditionDto.icon
        )
    }
}