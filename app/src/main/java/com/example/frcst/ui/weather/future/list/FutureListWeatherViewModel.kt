package com.example.frcst.ui.weather.future.list

import com.example.frcst.data.provider.UnitProvider
import com.example.frcst.data.repository.ForecastRepository
import com.example.frcst.internal.lazyDeferred
import com.example.frcst.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate

class FutureListWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weatherEntries by lazyDeferred{
        forecastRepository.getFutureWeatherList(LocalDate.now(), super.isMetricUnit)
    }
}
