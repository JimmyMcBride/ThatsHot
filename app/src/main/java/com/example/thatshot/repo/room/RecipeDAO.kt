package com.example.thatshot.repo.room

import androidx.room.*
import com.example.thatshot.repo.models.DummyRecipe

@Dao
interface RecipeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipeEntry(recipe: DummyRecipe)

    @Query("SELECT * FROM recipe_entry")
    suspend fun grabAllRecipes(): List<DummyRecipe>

    @Delete
    suspend fun deleteRecipe(recipe: DummyRecipe)

}