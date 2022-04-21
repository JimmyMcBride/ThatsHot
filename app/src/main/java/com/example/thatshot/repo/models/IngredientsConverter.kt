package com.example.thatshot.repo.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IngredientConverter {
    @TypeConverter
    fun fromCountryLangList(countryLang: List<Ingredient?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Ingredient?>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toCountryLangList(countryLangString: String?): List<Ingredient?>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Ingredient?>?>() {}.type
        return gson.fromJson(countryLangString, type)
    }
}