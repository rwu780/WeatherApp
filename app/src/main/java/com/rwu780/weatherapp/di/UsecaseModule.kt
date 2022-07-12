package com.rwu780.weatherapp.di

import com.rwu780.weatherapp.domain.WeatherRepository
import com.rwu780.weatherapp.domain.usecases.GetWeatherByCityName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UsecaseModule {

    @Provides
    @Singleton
    fun provideGetWeatherByCityNameUsecases(repository: WeatherRepository) =
        GetWeatherByCityName(repository)
}