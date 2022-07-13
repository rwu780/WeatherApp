package com.rwu780.weatherapp.domain.usecases

import android.location.Location
import com.rwu780.weatherapp.domain.location.LocationTracker
import javax.inject.Inject


class GetCurrentLocation @Inject constructor(
    private val locationTracker: LocationTracker
) {

    suspend operator fun invoke() : Location? {
        return locationTracker.getCurrentLocation()
    }
}