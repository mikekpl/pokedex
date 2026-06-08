# Add project specific ProGuard rules here.
# Obfuscation is enabled only for Android builds (does not affect iOS).
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# ===== Kotlin =====
-keep class kotlin.** { *; }
-keep class kotlinx.** { *; }
-dontwarn kotlin.**
-dontwarn kotlinx.**

# ===== Kotlin Serialization =====
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** { *** Companion; }
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}
-keep,includedescriptorclasses class com.mikelau.**$$serializer { *; }
-keepclassmembers class com.mikelau.** {
    *** Companion;
}
-keepclasseswithmembers class com.mikelau.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# ===== Compose =====
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# ===== Koin =====
-keep class org.koin.** { *; }
-dontwarn org.koin.**
-keep class com.mikelau.**.di.** { *; }

# ===== Ktor =====
-keep class io.ktor.** { *; }
-dontwarn io.ktor.**

# ===== Coil =====
-keep class coil3.** { *; }
-dontwarn coil3.**

# ===== App Entry Points =====
-keep class com.mikelau.pokedex.MainActivity { *; }

# ===== Shared Module (KMP) =====
-keep class com.mikelau.pokedex.AppKt { *; }
-keep class com.mikelau.pokedex.navigation.** { *; }

# ===== Core Modules =====
-keep class com.mikelau.core.** { *; }

# ===== Feature Modules =====
-keep class com.mikelau.feature.pokemon.domain.model.** { *; }
-keep class com.mikelau.feature.pokemon.domain.usecase.** { *; }
-keep class com.mikelau.feature.pokemon.domain.repository.** { *; }
-keep class com.mikelau.feature.pokemon.data.** { *; }
-keep class com.mikelau.feature.pokemon.ui.screen.** { *; }
-keep class com.mikelau.feature.pokemon.ui.di.** { *; }

-keep class com.mikelau.feature.pokemondetails.domain.model.** { *; }
-keep class com.mikelau.feature.pokemondetails.domain.usecase.** { *; }
-keep class com.mikelau.feature.pokemondetails.domain.repository.** { *; }
-keep class com.mikelau.feature.pokemondetails.data.** { *; }
-keep class com.mikelau.feature.pokemondetails.ui.screen.** { *; }
-keep class com.mikelau.feature.pokemondetails.ui.di.** { *; }

# ===== Navigation3 =====
-keep class androidx.navigation3.** { *; }
-dontwarn androidx.navigation3.**
