package com.example.thatshot.data.local

import androidx.room.TypeConverter
import com.example.thatshot.domain.model.Ingredient
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class DatabaseConverter {
    private val type = Types.newParameterizedType(List::class.java, Ingredient::class.java)
    private val adapter = Moshi.Builder().build().adapter<List<Ingredient>>(type)

    @TypeConverter
    fun convertIngredientListToString(list: List<Ingredient>): String {
        return adapter.toJson(list)
    }

    @TypeConverter
    fun convertStringToIngredientList(string: String): List<Ingredient> {
        return adapter.fromJson(string).orEmpty()
    }
}