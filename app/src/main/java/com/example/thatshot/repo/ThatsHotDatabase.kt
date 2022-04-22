package com.example.thatshot.repo

import android.content.Context
import androidx.room.*
import com.example.thatshot.repo.models.IngredientConverter
import com.example.thatshot.repo.models.Recipe

@Database(entities = [Recipe::class], exportSchema = false, version = 3)
@TypeConverters(IngredientConverter::class)
abstract class ThatsHotDatabase: RoomDatabase() {
    abstract fun thatsHotDao(): ThatsHotDao

    companion object {
        private const val DB_NAME = "THATS_HOT_DB"

        fun getDatabaseInstance(context: Context): ThatsHotDatabase {
            return Room.databaseBuilder(context, ThatsHotDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}