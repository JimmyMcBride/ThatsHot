package com.example.thatshot.repo.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IngredientConverter {
    @TypeConverter
    fun fromStringToList(listOfIngredients: List<Ingredient?>?): String? {
        if (listOfIngredients == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Ingredient?>?>() {}.type
        return gson.toJson(listOfIngredients, type)
    }

    @TypeConverter
    fun fromListToString(listOfIngredients: String?): List<Ingredient?>? {
        if (listOfIngredients == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Ingredient?>?>() {}.type
        return gson.fromJson(listOfIngredients, type)
    }
}