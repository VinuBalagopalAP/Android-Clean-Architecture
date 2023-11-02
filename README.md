# Android Clean Architecture with Jetpack Compose

This repository showcases an Android project employing Clean Architecture principles with Jetpack Compose, along with several important libraries and concepts such as Coroutines, Retrofit, KSP, ViewModel, Room Database, LiveData, and Hilt (Dagger 2).

## Overview

The project is designed to demonstrate the implementation of Clean Architecture on the Android platform. It leverages Jetpack Compose for building the user interface, Coroutines for asynchronous programming, Retrofit for handling network requests, KSP for compile-time processing, ViewModel for managing UI-related data, Room Database for local data persistence, LiveData for observing data changes, and Hilt for dependency injection.

## Architecture

The project follows the principles of Clean Architecture, emphasizing separation of concerns and maintainability. It's organized into layers:

- **Presentation**: Houses the Jetpack Compose UI components, ViewModels, and LiveData for handling the UI-related logic.
- **Domain**: Contains the business logic and use cases of the application, independent of any framework or platform.
- **Data**: Responsible for interacting with external data sources like networks or local databases using repositories and data sources.

## Key Libraries and Concepts

- **Jetpack Compose**: Modern Android UI toolkit using a declarative paradigm.
- **Coroutines**: Provides easy-to-use asynchronous programming.
- **Retrofit**: A type-safe HTTP client for making network requests.
- **KSP (Kotlin Symbol Processing)**: A compiler plugin for performing additional tasks at compile time.
- **ViewModel**: Manages UI-related data in a lifecycle-conscious way.
- **Room Database**: A SQLite object mapping library providing an abstraction layer over SQLite to allow robust database access.
- **LiveData**: Lifecycle-aware data holder for observing and reacting to data changes.
- **Hilt (Dagger 2)**: Dependency injection library for Android that reduces boilerplate code.


## License

This project is licensed under the [MIT License](LICENSE).
