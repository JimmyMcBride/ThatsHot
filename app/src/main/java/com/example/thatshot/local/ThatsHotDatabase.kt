package com.example.thatshot.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.thatshot.local.dao.ThatsHotDao
import com.example.thatshot.repo.models.DummyRecipe

@Database(entities = [DummyRecipe::class], version = 1)
abstract class ThatsHotDatabase : RoomDatabase() {

    abstract fun thatsHotDao(): ThatsHotDao

    companion object {

        private const val DATABASE_NAME = "recipe.db"

        // For Singleton instantiation
        @Volatile
        private var instance: ThatsHotDatabase? = null

        fun getInstance(context: Context): ThatsHotDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database.
        private fun buildDatabase(context: Context): ThatsHotDatabase {
            return Room.databaseBuilder(context, ThatsHotDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}