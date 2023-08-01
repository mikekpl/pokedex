package com.mikelau.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
) {
    fun getId(): String {
        val pattern = """/(\d+)/?$""".toRegex()
        val match = pattern.find(url)
        val pokemonNumber = match?.value?.substringBeforeLast("/") ?: "0"
        return pokemonNumber.replace("/", "")
    }
}