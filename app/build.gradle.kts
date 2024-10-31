@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinKsp)
    alias(libs.plugins.compose.compiler)
}

android {

    namespace = "com.mikelau.pokedex"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mikelau.pokedex"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "2.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["androidx.test.uiautomator.debug.UiAutomatorTimeout"] = "60000" // Increase timeout in milliseconds (default 20000)
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    lint {
        baseline = file("lint-baseline.xml")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
    tasks.withType<Test> {
        useJUnitPlatform()
        // options.parallel = false // Disable parallel downloading
    }
    packagingOptions {
        resources.excludes.add("META-INF/LICENSE.md")
        resources.excludes.add("META-INF/LICENSE-notice.md")
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

composeCompiler {
    enableStrongSkippingMode = true
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
}

dependencies {
    // Core Common
    implementation(project(":core:common"))
    implementation(project(":core:feature"))

    // Feature Pokemon List
    implementation(project(":feature:pokemon:ui"))
    implementation(project(":feature:pokemon:domain"))
    implementation(project(":feature:pokemon:data"))

    // Feature Pokemon Details
    implementation(project(":feature:pokemondetails:ui"))
    implementation(project(":feature:pokemondetails:domain"))
    implementation(project(":feature:pokemondetails:data"))

    // Libraries
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.material.icons.extended)
    implementation(libs.navigation.compose)
    implementation(libs.splashscreen)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.hilt)
    implementation(libs.hilt.testing)
    implementation(libs.google.gson)
    implementation(libs.androidx.uiautomator)
    testImplementation(project(":core:network"))
    ksp(libs.hilt.compiler)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)
    testImplementation(libs.robolectric)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.junit.jupiter)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.androidx.test.ext.junit)
    testImplementation(libs.core.testing)
    testImplementation(libs.nhaarman.mockito.kotlin)
    testImplementation(libs.lifecycle.common)
    androidTestImplementation(libs.kotlinx.coroutines.core)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.junit.api)
    androidTestImplementation(libs.junit.engine)
    androidTestImplementation("de.mannodermaus.junit5:android-test-core:1.3.0")
    androidTestImplementation("de.mannodermaus.junit5:android-test-runner:1.3.0")
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)
    runtimeOnly(libs.androidx.test.uiautomator) // exclude group: 'androidx.test', module: 'uiautomator'
    androidTestImplementation(libs.androidx.test.orchestrator)
    androidTestUtil(libs.androidx.test.orchestrator)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.mockito.core)
    androidTestImplementation(libs.mockito.android)
    androidTestImplementation(libs.mockito.junit.jupiter)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(project(":core:network"))
    androidTestImplementation(libs.navigation.testing)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    debugImplementation("androidx.test:monitor:1.6.0") {
        isTransitive = false
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}