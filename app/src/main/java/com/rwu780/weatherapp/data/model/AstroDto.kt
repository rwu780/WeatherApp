package com.rwu780.weatherapp.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class AstroDto(
    @Json(name = "moon_illumination")
    val moonIllumination: String?,
    @Json(name = "moon_phase")
    val moonPhase: String?,
    @Json(name = "moonrise")
    val moonrise: String?,
    @Json(name = "moonset")
    val moonset: String?,
    @Json(name = "sunrise")
    val sunrise: String?,
    @Json(name = "sunset")
    val sunset: String?
)