package com.example.thatshot.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.thatshot.model.models.Ingredient
import com.example.thatshot.model.models.Recipe

@Database(entities = [Recipe::class, Ingredient::class], exportSchema = false, version = 2)
abstract class JimmysCookbookDatabase: RoomDatabase() {
    abstract fun ingredientDao(): IngredientDao
    abstract fun recipeDao(): RecipeDao

    companion object {
        private const val DB_NAME = "JIMMYS_COOKBOOK_DB"

        fun getDatabaseInstance(context: Context): JimmysCookbookDatabase {
            return Room.databaseBuilder(context, JimmysCookbookDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}