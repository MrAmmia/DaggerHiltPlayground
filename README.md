# Dagger-Hilt Playground

This project demonstrates the use of Dependency Injection with the **Dagger-Hilt** library for making network calls and caching the retrieved data in a persistent **Room database**. It follows the **Model-View-Intent (MVI)** architecture pattern, ensuring a clean, scalable, and maintainable codebase.

## Features

- **Dagger-Hilt for Dependency Injection**: Simplifies dependency injection by managing the lifecycle of dependencies and injecting them where needed.
- **Network Calls**: Fetches data from a remote API using Retrofit.
- **Room Database**: Caches network data in a persistent Room database for offline access.
- **MVI Architecture**: Implements Model-View-Intent to handle user intents, manage UI state, and ensure testability and separation of concerns.

## Screenshots

## Tech Stack

- **Programming Language**: Kotlin
- **Dependency Injection**: Dagger-Hilt
- **Network Library**: Retrofit
- **Database**: Room
- **Architecture**: Model-View-Intent (MVI)
- **Coroutines/Flow**: For managing asynchronous tasks and reactive programming

## Project Structure

- **DI Setup**: Dagger-Hilt is used to inject dependencies like Retrofit, Room database, and repository objects.
- **Repository Pattern**: The repository mediates between network and local data sources.
- **Network Layer**: Uses Retrofit to make HTTP calls and fetch data from an API.
- **Caching**: Caches API responses in a Room database to allow offline access.
- **UI Layer**: Implements the MVI architecture pattern, where the View listens to state changes and renders the UI accordingly.

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/MrAmmia/DaggerHiltPlayground.git
   cd DaggerHiltPlayground
2. **Build the project**: Open the project in Android Studio and sync the Gradle dependencies.
3. **Run the App**: Build and run the project on an Android emulator or a physical device.

## How It Works

  - **Dependency Injection (Dagger-Hilt)**: Dagger-Hilt provides a straightforward way to inject dependencies like the Retrofit instance for network calls and the Room database for local persistence.
  - **Network Layer**: The app makes network requests using Retrofit and caches the response in a Room database. Network responses are handled asynchronously with Kotlin Coroutines and Flow.
  - **MVI Architecture**: The Model-View-Intent architecture ensures clean state management by handling user interactions through intents, updating the View based on the state emitted from the ViewModel.

## Contributing
Contributions are welcome! Feel free to open issues, fork the repo, and submit pull requests.
