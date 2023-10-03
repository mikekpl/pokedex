# Pokédex using Jetpack Compose
[![Android Arsenal](https://img.shields.io/badge/X-mike14u-blue.svg)](https://www.x.com/mike14u)
[![Android Arsenal](https://img.shields.io/badge/Github-mike14u-ff69b4.svg)](https://github.com/mike14u)

A Pokémon list project that exhibits the next generation way for us to build our multi-module Android Applications using Jetpack Compose, Hilt, Clean Architecture, Kotlin Flow and more...

<p align="center">
<img src="https://raw.githubusercontent.com/mike14u/pokedex/main/images/showcase.png" />
</p>

## Overview

This project will be helpful for many things both Pokémon and Tech, building with Jetpack Compose

<img src="https://raw.githubusercontent.com/mike14u/pokedex/main/images/gif.gif" height="800" />

### Pokédex Features
* **List of Pokémon:** #1010 as of the moment since the 5 others are newly released no further info is provided
* **Shiny Pokémon Image:** Get to see what their shiny form looks like in the details page
* **Search Pokémon:** Either search by their name or number
* **Responsive Layout:** Supports both portrait and landscape orientation
* **Android OS Support:** Supports Android Phones starting Nougat (Android 7.0 API Level 24) onwards

### Tech Features
<img src="https://raw.githubusercontent.com/mike14u/pokedex/main/images/directory.png" />

* **Multi-module:** App is multi-module ready to better isolate feature and layer modules that can be handled by big teams. Better way to reuse certain feature and spend less on build times as well
* **Clean Architecture:** Uses modern android way of building using Clean Architecture

<p align="center">
<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPxJYFYDDFI8-kdeuQrCEkocLizXyUhbsTyQ" width="400"/>
<img src="https://miro.medium.com/v2/resize:fit:1400/1*vcnYWWn_zhNk6I30meBaPg.png" width="400"/>
</p>
<p align="center">
Each feature module is built with a sub-module that follows Domain, Data and UI
</p>

* **Navigation Across Multi-module Features:** Access different features from different module using composables
* **Navigation Compose Deeplinks:** Shows an example on how to achieve deeplinks, parameterized deeplinks in Navigation Compose

> Can be tested using Firefox Android App
> Home or Main page use:
> pokedex:// or any other such as pokedex://home
> Details page use:
> pokedex://pokemon/{id} where id is the pokemon number

* **Coil Image Loading:** Attempt to load an image using Coil with or without loading animation
* **CI/CD Using Github:** Utilizing Github Actions and Github Releases page for pipelines and deployment (uses debug apk but you can build release or sign as you like)
* **R8:** Release and R8 ready, just make sure to generate your own keystore / signing capability
* **Lib Versions Catalog:** Uses new lib.versions.toml versions catalog to manage dependencies and their versions

## Built With

This project is built using next generation tools supported by the latest Canary version of **Android Studio Iguana | 2023.2.1 Canary 6:**. It is very important that your Kotlin, K2 Compiler, Jetpack Compose Compiler and Hilt versions aligned. In this case I used **(Kotlin 1.9.0, Jetpack Compose Compiler 1.5.0, Hilt 2.47)**

* **Android Studio Iguana:** IDE used
* **Kotlin:** Programming Language
* **K2 Compiler:** In preparation for Kotlin 2.0
* **Jetpack Compose:** UI Toolkit
* **Jetpack Libraries:** Lifecycle, View Model, Material 3, Navigation Compose etc.
* **Hilt:** Dependency Injection
* **Coil:** Image Loading
* **Retrofit & OkHttp:** Networking and API management
* **Kotlin Coroutines & Kotlin Flow:** Asynchrounous tasks and management

## Credits

<p align="center">
<img src="https://cdn.dribbble.com/users/1767024/screenshots/16833947/media/c9ba6952aa78a2e8365a9c03c1feec41.jpg?resize=1000x750&vertical=center" />
</p>

* Artwork and UI mockup reference: [https://dribbble.com/shots/16833947-Mobile-Pokedex-App-Design-Exploration](https://dribbble.com/shots/16833947-Mobile-Pokedex-App-Design-Exploration)
* API and Splashscreen Icon: [https://pokeapi.co/](https://pokeapi.co/)
* Splashscreen Main Icon: [https://icon-library.com/icon/pokedex-icon-21.html](https://icon-library.com/icon/pokedex-icon-21.html)
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