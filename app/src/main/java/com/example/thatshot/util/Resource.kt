package com.example.thatshot.util

sealed class Resource<T>(data: T? = null, errorMsg: String? = null) {
    class Loading<T>(): Resource<T>()
    data class Success<T>(val data: T): Resource<T>(data)
    data class Error<T>(val errorMsg: String): Resource<T>(null, errorMsg)

}
