package com.example.thatshot.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.thatshot.repo.models.DummyIngredient
import com.example.thatshot.repo.models.DummyRecipe

@Dao
interface ThatsHotDao {
    @Query("SELECT * FROM dummyRecipe")
    suspend fun getAll(): List<DummyRecipe>

    @Insert
    suspend fun insert(recipe: List<DummyRecipe>)

    @Delete
    suspend fun delete(recipe: List<DummyRecipe>)
}