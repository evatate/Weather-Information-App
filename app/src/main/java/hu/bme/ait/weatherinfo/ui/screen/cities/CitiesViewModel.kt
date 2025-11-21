package hu.bme.ait.weatherinfo.ui.screen.cities

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor() : ViewModel() {

    private val _cities = MutableStateFlow<List<String>>(emptyList())
    val cities: StateFlow<List<String>> = _cities.asStateFlow()

    fun addCity(cityName: String) {
        if (cityName.isNotBlank() && !_cities.value.contains(cityName)) {
            _cities.value = _cities.value + cityName
        }
    }

    fun removeCity(cityName: String) {
        _cities.value = _cities.value.filter { it != cityName }
    }
}