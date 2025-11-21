# Weather Info Application

## Overview

This Android application retrieves weather information from the OpenWeatherMap API. It demonstrates working with external APIs, parsing JSON data, and displaying the results in a clear, interactive mobile interface. The project shows experience in handling structured data, managing network requests with Retrofit, and building responsive UI components with Jetpack Compose.

## Features

* **City List Management:** Users can dynamically add and remove cities. The list is displayed using a performant, scrollable component.
* **Weather Details Screen:** For each city, the app fetches current weather information, including temperature, weather description, humidity, and sunrise/sunset times. Weather icons are downloaded and displayed dynamically.
* **Data Handling:** The app demonstrates structured data handling with JSON parsing using Kotlin Serialization and efficient network communication using Retrofit.
* **Clean UI and Architecture:** Built with Jetpack Compose for declarative UI, following MVVM architecture, and leveraging Hilt for dependency injection.

## Tech Stack

* Kotlin
* Android Jetpack Compose
* Retrofit (HTTP network communication)
* Kotlinx Serialization (JSON parsing)
* Coil (asynchronous image loading)
* Hilt (dependency injection)
* OpenWeatherMap API

## Skills Demonstrated

* Consuming and handling REST API data
* Parsing structured JSON responses into Kotlin data classes
* Displaying processed data in a clear, interactive mobile UI
* Applying modern Android architecture and dependency injection principles
* Building maintainable, modular, and testable mobile applications

## Setup

1. Clone the repository:

```bash
git clone https://github.com/evatate/WeatherInformation-App.git
```

2. Add your OpenWeatherMap API key in `key.properties`:

```
OPEN_WEATHER_API_KEY=your_api_key_here
```

3. Open the project in Android Studio and run.
