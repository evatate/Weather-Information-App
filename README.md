# Weather Info Application

An Android application designed for real-time retrieval and display of weather information from the OpenWeatherMap API. The project emphasizes structured data handling, efficient network communication, and a modular, maintainable architecture using modern Android development principles.

---

## Architecture Overview

The app follows a clean, modular architecture based on MVVM principles:

```
UI Layer (Jetpack Compose)
        │
        ▼
ViewModel Layer (State Management, Coroutines)
        │
        ▼
Repository Layer (Data Aggregation)
        │
        ▼
Data Sources (OpenWeatherMap API via Retrofit)
```

* **UI Layer:** Declarative components built with Jetpack Compose react to `StateFlow` updates from the ViewModel. Recomposition is minimized through careful state scoping.
* **ViewModel Layer:** Handles business logic, exposes state to UI, and launches coroutines for asynchronous network calls.
* **Repository Layer:** Centralizes data fetching and caching, combining API responses with local transformations.
* **Data Sources:** Retrofit interfaces fetch structured JSON data from OpenWeatherMap. JSON is parsed using Kotlinx Serialization into type-safe Kotlin data classes.

Dependency injection with **Hilt** decouples components, making each layer independently testable and replaceable.

---

## Data Flow

1. **User Interaction:** A city is added via the UI.
2. **State Update:** ViewModel captures the event and triggers a coroutine.
3. **Repository Fetch:** Repository checks for cached data, then requests current weather from OpenWeatherMap API.
4. **Network Request:** Retrofit sends the HTTP GET request asynchronously.
5. **JSON Parsing:** Kotlinx Serialization deserializes the response into structured Kotlin objects.
6. **UI Update:** ViewModel updates `StateFlow`, and Jetpack Compose recomposes only affected components.
7. **Dynamic Assets:** Weather icons are loaded via Coil and cached for efficient re-rendering.

This flow ensures non-blocking UI updates, type-safe data handling, and minimal memory overhead.

---

## Features in Depth

* **Dynamic City Management:** Cities are stored in a reactive list. Adding/removing cities triggers immediate recomposition of the list view, optimized to handle hundreds of entries without performance degradation.

* **Weather Details:**

  * Temperature, humidity, and weather description fetched in real time.
  * Sunrise/sunset times calculated and displayed in the local timezone.
  * Dynamic icons are asynchronously downloaded, cached, and displayed with minimal UI impact.

* **Error Handling:** Network errors, timeouts, and invalid responses are captured and propagated to the UI as structured states, allowing graceful user feedback.

* **Performance Optimizations:**

  * Lazy loading of list items with Compose’s `LazyColumn` to reduce memory footprint.
  * Asynchronous network calls and image loading prevent UI blocking.
  * Structured state management reduces unnecessary recompositions.

---

## Tech Stack

* **Kotlin + Coroutines**: Asynchronous programming and structured concurrency.
* **Jetpack Compose**: Declarative, reactive UI components.
* **Retrofit + Kotlinx Serialization**: Type-safe HTTP client and JSON parsing.
* **Coil**: Asynchronous image loading and caching.
* **Hilt**: Dependency injection for modular and testable code.
* **MVVM Architecture**: Separation of concerns for maintainable code.
* **OpenWeatherMap API**: Real-time weather data integration.

---

## Usage

1. Clone the repository:

```bash
git clone https://github.com/evatate/WeatherInformation-App.git
```

2. Add your OpenWeatherMap API key in **key.properties**:

```
OPEN_WEATHER_API_KEY=your_api_key_here
```

3. Open the project in Android Studio and run the application.

4. Interact with the app:

   * **Add City:** Enter a city name to fetch weather details.
   * **View Weather:** Tap a city for temperature, humidity, description, and sunrise/sunset.
   * **Remove City:** Swipe to remove from the tracked list.
