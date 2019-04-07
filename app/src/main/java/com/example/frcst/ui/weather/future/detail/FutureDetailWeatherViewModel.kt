package com.example.frcst.ui.weather.future.detail

import com.example.frcst.data.provider.UnitProvider
import com.example.frcst.data.repository.ForecastRepository
import com.example.frcst.internal.lazyDeferred
import com.example.frcst.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate

class FutureDetailWeatherViewModel(
    private val detailDate: LocalDate,
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weather by lazyDeferred{
        forecastRepository.getFutureWeatherByDate(detailDate, super.isMetricUnit)
    }
}
