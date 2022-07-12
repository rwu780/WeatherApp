package com.rwu780.weatherapp.di

import com.rwu780.weatherapp.data.WeatherApi
import com.rwu780.weatherapp.data.WeatherRepositoryImpl
import com.rwu780.weatherapp.domain.WeatherRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi() : Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideWeatherApi(moshi: Moshi): WeatherApi =
        Retrofit.Builder().baseUrl(WeatherApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(api)
    }

}