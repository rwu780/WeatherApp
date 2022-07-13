package com.rwu780.weatherapp.di

import android.content.Context
import com.rwu780.weatherapp.data.LocalDataRepository
import com.rwu780.weatherapp.domain.WeatherRepository
import com.rwu780.weatherapp.domain.usecases.GetWeatherByCityName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UsecaseModule {

    @Provides
    @Singleton
    fun provideLocalDataRepository(@ApplicationContext cxt: Context) : LocalDataRepository{
        return LocalDataRepository(cxt)
    }

    @Provides
    @Singleton
    fun provideGetWeatherByCityNameUsecases(repository: WeatherRepository) =
        GetWeatherByCityName(repository)
}