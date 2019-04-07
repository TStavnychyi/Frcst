package com.example.frcst

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.example.frcst.data.db.ForecastDatabase
import com.example.frcst.data.network.*
import com.example.frcst.data.provider.LocationProvider
import com.example.frcst.data.provider.LocationProviderImpl
import com.example.frcst.data.provider.UnitProvider
import com.example.frcst.data.provider.UnitProviderImpl
import com.example.frcst.data.repository.ForecastRepository
import com.example.frcst.data.repository.ForecastRepositoryImpl
import com.example.frcst.ui.weather.current.CurrentWeatherViewModelFactory
import com.example.frcst.ui.weather.future.detail.FutureDetailWeatherViewModelFactory
import com.example.frcst.ui.weather.future.list.FutureListWeatherViewModelFactory
import com.google.android.gms.location.LocationServices
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*
import org.threeten.bp.LocalDate

class ForecastApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind() from singleton { instance<ForecastDatabase>().futureWeatherDao() }
        bind() from singleton { instance<ForecastDatabase>().currentLocationDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }
        bind<LocationProvider>() with  singleton { LocationProviderImpl(instance(), instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance(), instance(), instance(), instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
        bind() from provider { FutureListWeatherViewModelFactory(instance(), instance()) }
        bind() from factory {detailDate: LocalDate -> FutureDetailWeatherViewModelFactory(detailDate, instance(), instance())}
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        //reads and writes all default values from R.xml.preferences to PreferenceManager object only for the first time, when app
        //is launched
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}