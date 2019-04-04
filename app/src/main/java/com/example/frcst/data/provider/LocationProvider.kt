package com.example.frcst.data.provider

import com.example.frcst.data.db.entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend  fun getPreferredLocationString(): String
}