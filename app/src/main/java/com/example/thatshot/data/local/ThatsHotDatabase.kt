package com.example.thatshot.data.local

import android.content.Context
import androidx.room.*
import com.example.thatshot.domain.model.Recipe
import com.example.thatshot.utils.Constants.DATABASE_NAME

@Database(entities = [Recipe::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class ThatsHotDatabase : RoomDatabase() {
    companion object {
        fun create(context: Context): ThatsHotDatabase {
            return Room.databaseBuilder(context, ThatsHotDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration().build()
        }
    }

    abstract fun thatsHotDao(): ThatsHotDao
}