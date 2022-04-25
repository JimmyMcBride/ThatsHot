package com.example.thatshot.repo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.thatshot.repo.models.DummyRecipe

@Database(entities = [DummyRecipe::class], exportSchema = false, version = 1)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDAO(): RecipeDAO

    companion object {

        const val DB_NAME = "RECIPE_DB"

        fun getDatabaseInstance(context: Context): RecipeDatabase {
            return Room
                .databaseBuilder(context, RecipeDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

    }

}