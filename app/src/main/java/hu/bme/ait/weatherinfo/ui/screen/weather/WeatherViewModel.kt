package hu.bme.ait.weatherinfo.ui.screen.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.ait.weatherinfo.data.WeatherResponse
import hu.bme.ait.weatherinfo.data.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val weather: WeatherResponse) : WeatherState()
    data class Error(val message: String) : WeatherState()
}

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState.asStateFlow()

    fun getWeather(cityName: String) {
        viewModelScope.launch {
            _weatherState.value = WeatherState.Loading

            weatherRepository.getWeather(cityName)
                .onSuccess { weather ->
                    _weatherState.value = WeatherState.Success(weather)
                }
                .onFailure { exception ->
                    _weatherState.value = WeatherState.Error(
                        exception.message ?: "Unknown error occurred"
                    )
                }
        }
    }
}