package com.example.frcst.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.example.frcst.data.provider.UnitProvider
import com.example.frcst.data.repository.ForecastRepository
import com.example.frcst.internal.UnitSystem
import com.example.frcst.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {
    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred{
        forecastRepository.getCurrentWeather(isMetric)
    }

    val weatherLocation by lazyDeferred{
        forecastRepository.getWeatherLocation()
    }


}
