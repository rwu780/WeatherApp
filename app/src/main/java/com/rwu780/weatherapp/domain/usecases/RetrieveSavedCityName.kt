package com.rwu780.weatherapp.domain.usecases

import android.util.Log
import com.rwu780.weatherapp.data.LocalDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrieveSavedCityName @Inject constructor(
    private val localDataRepository: LocalDataRepository
) {

    suspend operator fun invoke() : String {
        Log.d("in", "invoke: ")
        return localDataRepository.getCityNameFromDataStore()
    }
}