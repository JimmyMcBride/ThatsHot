package com.example.thatshot.model.room

import androidx.room.*
import com.example.thatshot.model.models.Recipe

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEditRecipe(recipe: Recipe)

    @Query("UPDATE recipes SET name= :name WHERE id= :id")
    suspend fun updateRecipeName(id: Int, name: String)

    @Query("UPDATE recipes SET description= :description WHERE id= :id")
    suspend fun updateRecipeDescription(id: Int, description: String)

    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<Recipe>

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("SELECT count(*) FROM recipes")
    suspend fun countRecipes(): Int
}