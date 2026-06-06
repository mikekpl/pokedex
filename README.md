# Pokédex — Kotlin Multiplatform (Compose Multiplatform)

A Pokémon list project that exhibits the modern way to build **cross-platform mobile applications** targeting both Android and iOS using Kotlin Multiplatform, Compose Multiplatform, Koin, Ktor, Clean Architecture, and more.

<p align="center">
<img src="https://raw.githubusercontent.com/mike14u/pokedex/main/images/showcase.png" />
</p>

## Overview

This project demonstrates building a production-ready KMP application with a multi-module, clean architecture structure that runs natively on both Android and iOS from a single shared Kotlin codebase.

<img src="https://raw.githubusercontent.com/mike14u/pokedex/main/images/gif.gif" height="800" />

### Pokédex Features
* **List of Pokémon:** #1025 Official Species and all the other variants including regional variants, mega evolutions and gigantamax forms
* **Shiny Pokémon Image:** Get to see what their shiny form looks like in the details page
* **Search Pokémon:** Either search by their name or number
* **Responsive Layout:** Supports both portrait and landscape orientation
* **Android OS Support:** Android 7.0 (API Level 24) and above
* **iOS Support:** iOS 16.0 and above (iosArm64 + iosSimulatorArm64)

### Tech Features
<img src="https://raw.githubusercontent.com/mike14u/pokedex/main/images/directory.png" />

* **Kotlin Multiplatform (KMP):** Fully multiplatform — shared business logic, networking, DI, and UI across Android and iOS
* **Compose Multiplatform:** Single shared Compose UI codebase targeting both platforms
* **Multi-module:** Feature and layer modules (domain, data, ui) per feature for team scalability and build time improvements
* **Clean Architecture:** Domain → Data → UI separation per feature module

<p align="center">
<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPxJYFYDDFI8-kdeuQrCEkocLizXyUhbsTyQ" width="400"/>
<img src="https://miro.medium.com/v2/resize:fit:1400/1*vcnYWWn_zhNk6I30meBaPg.png" width="400"/>
</p>
<p align="center">
Each feature module is built with sub-modules following Domain, Data and UI
</p>

* **Navigation 3 (JetBrains KMP):** Type-safe multiplatform navigation using `org.jetbrains.androidx.navigation3` with a sealed `NavKey` back stack
* **Compose Resources:** SVG icons loaded via Coil from `composeResources/files/` — cross-platform, no platform-specific drawables
* **Edge-to-Edge:** Full edge-to-edge support on Android with dark status bar icons; iOS uses `.preferredColorScheme(.light)`
* **Uses AGP 9!**
* **Coil 3 Image Loading:** KMP-compatible Coil 3 with SVG decoder (`coil-svg`) and Ktor network engine (`coil-network-ktor3`)
* **Lib Versions Catalog:** Uses `libs.versions.toml` to manage all dependencies and versions

## Built With

Built with **Android Studio Quail 1 | 2026.1.1** and **Xcode** for iOS. Kotlin 2.4.0 with Compose Compiler Gradle plugin.

* **Android Studio Quail / Xcode:** IDEs used
* **Kotlin Multiplatform:** Shared code across Android and iOS
* **Compose Multiplatform 1.11.1:** Shared UI toolkit
* **K2 Compiler:** Enabled
* **Jetpack Libraries:** Lifecycle, ViewModel, Material 3, Navigation 3 (KMP)
* **Koin 4.x:** Multiplatform Dependency Injection via `KoinApplication` composable in `commonMain`
* **Ktor 3.x:** Multiplatform HTTP client (OkHttp engine on Android, Darwin engine on iOS)
* **Coil 3.x:** Multiplatform image loading with SVG support
* **Kotlin Coroutines & Flow:** Asynchronous tasks — `Dispatchers.IO` available on all KMP targets (coroutines 1.10+)
* **Explicit backing fields** usage in ViewModels

## Project Structure

```
composeApp/          ← App entry point (Android + iOS)
  androidMain/       ← MainActivity, BaseApplication
  commonMain/        ← Shared App.kt, navigation, DI modules
  iosMain/           ← MainViewController

core/
  common/            ← Shared UI utilities, colors, UiEvents
  feature/           ← FeatureApi, AppNavDestination (NavKey)
  network/           ← Ktor HttpClient, ApiService, NetworkModule

feature/
  pokemon/
    data/            ← Repository impl, mappers
    domain/          ← Use cases, repository interface, Koin module
    ui/              ← ViewModel, Screens, Koin module, SVG resources
  pokemondetails/
    data/            ← Repository impl, mappers
    domain/          ← Use cases, repository interface, Koin module
    ui/              ← ViewModel, Screens, Koin module, SVG resources

iosApp/              ← Xcode project (Swift entry point)
```

## Credits

<p align="center">
<img src="https://cdn.dribbble.com/users/1767024/screenshots/16833947/media/c9ba6952aa78a2e8365a9c03c1feec41.jpg?resize=1000x750&vertical=center" />
</p>

* Artwork and UI mockup reference: [https://dribbble.com/shots/16833947-Mobile-Pokedex-App-Design-Exploration](https://dribbble.com/shots/16833947-Mobile-Pokedex-App-Design-Exploration)
* API: [https://pokeapi.co/](https://pokeapi.co/)
* App Launcher Icon: [https://dribbble.com/shots/2947190-Poke-Ball-Pokedex](https://dribbble.com/shots/2947190-Poke-Ball-Pokedex)

## License

```
Copyright 2023 Mike Lau

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
