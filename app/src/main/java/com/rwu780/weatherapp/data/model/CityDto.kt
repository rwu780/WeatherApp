package com.rwu780.weatherapp.data.model


import com.rwu780.weatherapp.domain.model.City
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityDto(
    @Json(name = "country")
    val country: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "lat")
    val lat: Double?,
    @Json(name = "lon")
    val lon: Double?,
    @Json(name = "name")
    val name: String,
    @Json(name = "region")
    val region: String,
    @Json(name = "url")
    val url: String?
) {

    fun toCity() : City {
        return City(
            name = name,
            region = region
        )
    }
}