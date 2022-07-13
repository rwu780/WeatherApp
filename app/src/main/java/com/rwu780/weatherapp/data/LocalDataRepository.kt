package com.rwu780.weatherapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataRepository @Inject constructor(
    val context: Context
) {

    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name="com.rwu780.weatherApp")

    companion object {
        val UNITS = booleanPreferencesKey("temperature_units")
        val CITY = stringPreferencesKey("city_name")
    }

    suspend fun saveCityNameToDataStore(cityName: String){
        context.dataStore.edit {
            it[CITY] = cityName
        }
    }

    fun getCityNameFromDataStore() = context.dataStore.data.map { preferences ->
        preferences[CITY] ?: ""
    }

}

