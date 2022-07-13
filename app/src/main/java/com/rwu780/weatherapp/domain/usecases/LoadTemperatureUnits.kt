package com.rwu780.weatherapp.domain.usecases

import com.rwu780.weatherapp.data.LocalDataRepository
import com.rwu780.weatherapp.domain.Temperature_Units
import javax.inject.Inject

class LoadTemperatureUnits @Inject constructor(
    private val localDataRepository: LocalDataRepository
) {

    suspend operator fun invoke() : Temperature_Units {
        val value = localDataRepository.getTemperatureUnitsFromDataStore()
        return if (value == null){
            Temperature_Units.Fahrenheit
        } else {
            Temperature_Units.valueOf(value)
        }
    }
}