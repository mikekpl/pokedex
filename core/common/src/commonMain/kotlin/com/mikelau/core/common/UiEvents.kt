package com.mikelau.core.common

sealed class UiEvents<T>(val data:T? = null, val message: String? = null) {

    class Loading<T>: UiEvents<T>()
    class Success<T>(data:T): UiEvents<T>(data = data)
    class Error<T>(message: String): UiEvents<T>(message = message)

}