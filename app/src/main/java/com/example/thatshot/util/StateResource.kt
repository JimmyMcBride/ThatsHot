package com.example.thatshot.util

sealed class StateResource<T>  {
    data class Success<T>(val data: T): StateResource<T>()
    data class Error<T>(val err: Throwable): StateResource<T>()
    class Loading<T>: StateResource<T>()
    class Standby<T>: StateResource<T>()
}