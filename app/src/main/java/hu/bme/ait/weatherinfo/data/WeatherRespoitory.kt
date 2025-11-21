package hu.bme.ait.weatherinfo.data

import hu.bme.ait.weatherinfo.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherAPI: WeatherAPI
) {
    private val apiKey = BuildConfig.OPENWEATHER_API_KEY

    suspend fun getWeather(cityName: String): Result<WeatherResponse> {
        return try {
            val url = "https://api.openweathermap.org/data/2.5/weather?q=$cityName&units=metric&appid=$apiKey"

            val response = weatherAPI.getWeather(
                city = cityName,
                units = "metric",
                apiKey = apiKey
            )
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}