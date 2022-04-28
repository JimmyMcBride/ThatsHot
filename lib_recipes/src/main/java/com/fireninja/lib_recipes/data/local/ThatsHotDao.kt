package com.fireninja.lib_recipes.data.local

import androidx.room.Dao
import androidx.room.Query
import com.fireninja.lib_recipes.domain.model.Recipe

@Dao
interface ThatsHotDao {
    @Query("select * from recipes order by id asc")
    fun getRecipes(): List<Recipe>
}