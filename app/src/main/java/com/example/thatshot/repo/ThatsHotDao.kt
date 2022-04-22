package com.example.thatshot.repo

import androidx.room.*
import com.example.thatshot.repo.models.Recipe

@Dao
interface ThatsHotDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(item: Recipe)

    @Query("SELECT * FROM recipes_table")
    suspend fun fetchAllRecipes(): MutableList<Recipe>

    @Update
    suspend fun updateRecipe(item: Recipe)

    @Query("DELETE FROM recipes_table WHERE id = :id")
    suspend fun deleteByID(id: Int)

    @Query("SELECT * FROM recipes_table WHERE id = :id")
    suspend fun fetchByID(id: Int): MutableList<Recipe>

    @Query("DELETE FROM recipes_table")
    suspend fun clearDB()
}