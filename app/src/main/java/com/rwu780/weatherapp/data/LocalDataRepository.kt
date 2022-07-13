package com.rwu780.weatherapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.rwu780.weatherapp.domain.Temperature_Units
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name="com.rwu780.weatherApp")

@Singleton
class LocalDataRepository @Inject constructor(
    private val context: Context
) {

    companion object {
        val CITY = stringPreferencesKey("city_name")
        val UNITS = stringPreferencesKey("temperature_units")
    }

    suspend fun saveCityNameToDataStore(cityName: String){
        context.dataStore.edit { preferences ->
            preferences[CITY] = cityName
        }
    }

    suspend fun getCityNameFromDataStore() : String {
        val preferences = context.dataStore.data.first()
        return preferences[CITY] ?: ""
    }

    suspend fun saveTemperatureUnitsToDataStore(units: String){
        context.dataStore.edit { preferences ->
            preferences[UNITS] = units
        }
    }

    suspend fun getTemperatureUnitsFromDataStore() : String? {
        val preferences = context.dataStore.data.first()
        return preferences[UNITS]
    }

}

