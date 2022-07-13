package com.rwu780.weatherapp.domain.usecases

import com.rwu780.weatherapp.data.LocalDataRepository
import com.rwu780.weatherapp.domain.Temperature_Units
import javax.inject.Inject

class SavedTemperatureUnits @Inject constructor(
    private val localDataRepository: LocalDataRepository
) {

    suspend operator fun invoke(units: String) {
        localDataRepository.saveTemperatureUnitsToDataStore(units)
    }
}