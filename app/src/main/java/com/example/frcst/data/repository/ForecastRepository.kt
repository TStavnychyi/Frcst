package com.example.frcst.data.repository

import androidx.lifecycle.LiveData
import com.example.frcst.data.db.entity.WeatherLocation
import com.example.frcst.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>

    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}