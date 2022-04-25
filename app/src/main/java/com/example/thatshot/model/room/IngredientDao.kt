package com.example.thatshot.model.room

import androidx.room.*
import com.example.thatshot.model.models.Ingredient

@Dao
interface IngredientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEditIngredient(ingredient: Ingredient)

    @Query("SELECT * FROM ingredients WHERE id= :recipeId")
    suspend fun getRecipeIngredients(recipeId: Int): List<Ingredient>

    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient)
}