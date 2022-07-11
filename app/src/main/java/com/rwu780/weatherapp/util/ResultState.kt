package com.rwu780.weatherapp.util

sealed class ResultState<T>(val data: T? = null, val message: String? = null) {

    class Loading<T> : ResultState<T>()
    class Success<T>(data: T?) : ResultState<T>(data)
    class Error<T>(errorMessage: String?, data: T? = null) : ResultState<T>(data, message = errorMessage)

}