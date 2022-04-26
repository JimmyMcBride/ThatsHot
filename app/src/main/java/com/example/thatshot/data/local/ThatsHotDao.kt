package com.example.thatshot.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.thatshot.domain.model.Recipe

@Dao
interface ThatsHotDao {
    @Query("select * from recipes order by id asc")
    fun getRecipes(): List<Recipe>
}