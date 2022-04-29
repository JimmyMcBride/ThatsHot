package com.example.thatshot.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.thatshot.domain.models.Ingredient
import com.example.thatshot.domain.models.Recipe

@Database(entities = [Ingredient::class, Recipe::class], exportSchema = false, version=6)
//@TypeConverters(DBConverter::class)
abstract class ThatsHotDatabase: RoomDatabase() {
    abstract fun thatsHotDao(): ThatsHotDao

}