package com.example.thatshot.utils

import com.example.thatshot.repo.models.DummyRecipe


sealed class Resource<T>(data: Any? = null, message: String?){
        data class Success<T>(val data: T): Resource<T>(data,null)
        class Loading<T> : Resource<T>(null,null)
        data class Error<T>(val message: String): Resource<T>(null,message)


}
