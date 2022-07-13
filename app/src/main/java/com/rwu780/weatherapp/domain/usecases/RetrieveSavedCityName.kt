package com.rwu780.weatherapp.domain.usecases

import com.rwu780.weatherapp.data.LocalDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrieveSavedCityName @Inject constructor(
    private val localDataRepository: LocalDataRepository
) {

    operator fun invoke() : Flow<String> {
        return localDataRepository.getCityNameFromDataStore()
    }
}