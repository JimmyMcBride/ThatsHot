package com.example.thatshot.repo.room

import androidx.room.*
import com.example.thatshot.repo.models.DummyRecipe

@Dao
interface RecipeQueries {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipe(recipe : DummyRecipe)


    @Query("SELECT * FROM recipeTable")
    suspend fun grabAllRecipes(): List<DummyRecipe>

    @Delete
    suspend fun deleteRecipe(todo: DummyRecipe)

    @Query("SELECT * FROM recipeTable WHERE id=:id ")
    fun SingleRecipe(id: Int): List<DummyRecipe>
}
