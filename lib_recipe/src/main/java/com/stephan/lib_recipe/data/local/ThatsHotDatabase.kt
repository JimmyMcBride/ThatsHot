package com.stephan.lib_recipe.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.stephan.lib_recipe.domain.models.Ingredient
import com.stephan.lib_recipe.domain.models.Recipe

@Database(entities = [Ingredient::class, Recipe::class], exportSchema = false, version=6)
//@TypeConverters(DBConverter::class)
abstract class ThatsHotDatabase: RoomDatabase() {
    abstract fun thatsHotDao(): ThatsHotDao

}