plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "feature-pokemon-data"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:pokemon:domain"))
            implementation(project(":core:network"))
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.mikelau.feature.pokemon.data"
    compileSdk = 36
    defaultConfig {
        minSdk = 24
    }
}
