package hu.bme.ait.weatherinfo.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("coord")
    val coord: Coord? = null,
    @SerialName("weather")
    val weather: List<Weather>? = null,
    @SerialName("base")
    val base: String? = null,
    @SerialName("main")
    val main: Main? = null,
    @SerialName("visibility")
    val visibility: Int? = null,
    @SerialName("wind")
    val wind: Wind? = null,
    @SerialName("clouds")
    val clouds: Clouds? = null,
    @SerialName("dt")
    val dt: Long? = null,
    @SerialName("sys")
    val sys: Sys? = null,
    @SerialName("timezone")
    val timezone: Int? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("cod")
    val cod: Int? = null
)

@Serializable
data class Coord(
    @SerialName("lon")
    val lon: Double? = null,
    @SerialName("lat")
    val lat: Double? = null
)

@Serializable
data class Weather(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("main")
    val main: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("icon")
    val icon: String? = null
)

@Serializable
data class Main(
    @SerialName("temp")
    val temp: Double? = null,
    @SerialName("feels_like")
    val feelsLike: Double? = null,
    @SerialName("temp_min")
    val tempMin: Double? = null,
    @SerialName("temp_max")
    val tempMax: Double? = null,
    @SerialName("pressure")
    val pressure: Int? = null,
    @SerialName("humidity")
    val humidity: Int? = null
)

@Serializable
data class Wind(
    @SerialName("speed")
    val speed: Double? = null,
    @SerialName("deg")
    val deg: Int? = null
)

@Serializable
data class Clouds(
    @SerialName("all")
    val all: Int? = null
)

@Serializable
data class Sys(
    @SerialName("type")
    val type: Int? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("sunrise")
    val sunrise: Long? = null,
    @SerialName("sunset")
    val sunset: Long? = null
)