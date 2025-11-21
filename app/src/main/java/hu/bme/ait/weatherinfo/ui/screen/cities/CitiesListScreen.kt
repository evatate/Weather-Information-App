package hu.bme.ait.weatherinfo.ui.screen.cities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesListScreen(
    onCityClick: (String) -> Unit,
    viewModel: CitiesViewModel = hiltViewModel()
) {
    val cities by viewModel.cities.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather Cities") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add City")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (cities.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No cities added yet.\nTap + to add a city",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(cities) { city ->
                        CityItem(
                            cityName = city,
                            onCityClick = { onCityClick(city) },
                            onDeleteClick = { viewModel.removeCity(city) }
                        )
                    }
                }
            }
        }

        if (showDialog) {
            AddCityDialog(
                onDismiss = { showDialog = false },
                onCityAdded = { cityName ->
                    viewModel.addCity(cityName)
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun CityItem(
    cityName: String,
    onCityClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCityClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = cityName,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onDeleteClick) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete City",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCityDialog(
    onDismiss: () -> Unit,
    onCityAdded: (String) -> Unit
) {
    var cityName by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add City") },
        text = {
            Column {
                OutlinedTextField(
                    value = cityName,
                    onValueChange = {
                        cityName = it
                        errorMessage = ""
                    },
                    label = { Text("City Name") },
                    placeholder = { Text("e.g., Budapest") },
                    singleLine = true,
                    isError = errorMessage.isNotEmpty()
                )
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (cityName.isBlank()) {
                        errorMessage = "Please enter a city name"
                    } else {
                        onCityAdded(cityName.trim())
                    }
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}