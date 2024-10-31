package com.mikelau.pokedex

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonProvider {

    inline fun <reified U> objectFromJsonFileWithType(filePath: String): U =
        Gson().fromJson(readResourceFile(filePath), object : TypeToken<U>() {}.type)

    fun readResourceFile(fileName: String): String? {
        val inputStream = this::class.java.getResourceAsStream(fileName)
        return if (inputStream != null) {
            try {
                inputStream.bufferedReader()
                    .use { it.readText() }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else {
            println("Error: Resource not found.")
            null
        }
    }

}