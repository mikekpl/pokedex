package com.mikelau.feature.pokemondetails.domain.model

data class PokemonDetails(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val type: List<String?>
) {
    fun getMetricHeight(): String {
        val x = height * 10
        return "$x cm"
    }

    fun getMetricWeight (): String {
        val x = weight.toDouble() / 100.0
        return "$x kg"
    }
}