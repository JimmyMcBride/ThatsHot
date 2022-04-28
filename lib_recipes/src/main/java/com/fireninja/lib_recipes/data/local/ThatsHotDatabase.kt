package com.fireninja.lib_recipes.data.local

import androidx.room.*
import com.fireninja.lib_recipes.domain.model.Recipe

@Database(entities = [Recipe::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class ThatsHotDatabase : RoomDatabase() {
    abstract fun thatsHotDao(): ThatsHotDao
}