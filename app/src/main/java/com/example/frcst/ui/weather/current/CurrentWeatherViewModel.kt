package com.example.frcst.ui.weather.current

import com.example.frcst.data.provider.UnitProvider
import com.example.frcst.data.repository.ForecastRepository
import com.example.frcst.internal.lazyDeferred
import com.example.frcst.ui.base.WeatherViewModel

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weather by lazyDeferred{
        forecastRepository.getCurrentWeather(super.isMetricUnit)
    }
}
