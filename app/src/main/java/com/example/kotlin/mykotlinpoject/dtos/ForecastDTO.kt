package com.example.kotlin.mykotlinpoject.dtos

import java.text.DateFormat
import java.util.*

class ForecastDTO{
    data class ForecastResult(val city: City, val list: List<Forecast>)

    data class City(val id: Long, val name: String, val coord: Coordinates, val country: String, val population: Int)

    data class Coordinates(val lon: Float, val lat: Float)

    data class Forecast(val dt: Long, val temp: Temperature, val pressure: Float, val humidity: Int, val weather: List<Weather>,
                        val speed: Float, val deg: Int, val clouds: Int, val rain: Float)

    data class Temperature(val day: Float, val min: Float, val max: Float, val  night: Float, val eve: Float, val morn: Float)

    data class Weather(val id: Long, val main: String, val description: String, val icon: String)

    data class ForecastList(val city: String, val country: String, val dailyForecast: List<ForecastDesc>) {
        operator fun get(position: Int) = dailyForecast[position]
        fun size() = dailyForecast.size
    }

    data class ForecastDesc(val date: String, val description: String, val high: Int, val low: Int, val iconUrl: String)

    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ForecastDesc> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ForecastDesc {
        return ForecastDesc(converData(forecast.dt), forecast.weather[0].description, forecast.temp.max.toInt(),
                forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"

    private fun converData(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }
}

