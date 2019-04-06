package com.example.frcst.ui.base

import androidx.lifecycle.ViewModel
import com.example.frcst.data.provider.UnitProvider
import com.example.frcst.data.repository.ForecastRepository
import com.example.frcst.internal.UnitSystem
import com.example.frcst.internal.lazyDeferred

abstract class WeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetricUnit: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }
}