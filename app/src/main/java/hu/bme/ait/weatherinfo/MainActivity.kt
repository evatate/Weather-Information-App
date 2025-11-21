package hu.bme.ait.weatherinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.ait.weatherinfo.ui.screen.cities.CitiesListScreen
import hu.bme.ait.weatherinfo.ui.screen.weather.WeatherDetailsScreenContent
import hu.bme.ait.weatherinfo.ui.theme.WeatherInfoTheme

object NavRoutes {
    const val Cities = "cities"
    const val WeatherDetails = "weather/{cityName}"
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherInfoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WeatherApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun WeatherApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Cities,
        modifier = modifier
    ) {
        composable(NavRoutes.Cities) {
            CitiesListScreen(
                onCityClick = { cityName ->
                    navController.navigate("weather/$cityName")
                }
            )
        }

        composable(NavRoutes.WeatherDetails) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            WeatherDetailsScreenContent(
                cityName = cityName,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
