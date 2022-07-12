package com.rwu780.weatherapp.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class ConditionDto(
    @Json(name = "code")
    val code: Int,
    @Json(name = "icon")
    val icon: String,
    @Json(name = "text")
    val text: String
)