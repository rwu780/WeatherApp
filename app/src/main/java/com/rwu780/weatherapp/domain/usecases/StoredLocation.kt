package com.rwu780.weatherapp.domain.usecases

import com.rwu780.weatherapp.data.LocalDataRepository
import javax.inject.Inject

class StoredLocation @Inject constructor(
    private val localDataRepository: LocalDataRepository
) {

    suspend operator fun invoke(cityName: String) {
        localDataRepository.saveCityNameToDataStore(cityName)
    }
}