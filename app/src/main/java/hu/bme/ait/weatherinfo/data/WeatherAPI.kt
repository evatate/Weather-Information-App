package hu.bme.ait.weatherinfo.data

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String
    ): WeatherResponse
}