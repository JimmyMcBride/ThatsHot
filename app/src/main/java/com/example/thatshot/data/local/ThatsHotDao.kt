package com.example.thatshot.data.local

import androidx.room.*
import com.example.thatshot.domain.models.Ingredient
import com.example.thatshot.domain.models.Recipe
import com.example.thatshot.util.RecipeAndIngredients

@Dao
interface ThatsHotDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredient(ingredient: Ingredient)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient)

    @Transaction
    @Query("SELECT * FROM recipe_table WHERE recipe = :recipe")
    suspend fun getRecipeAndIngredients(recipe: String): List<RecipeAndIngredients>

    @Query("SELECT * FROM recipe_table order by recipe asc")
    suspend fun getRecipes(): List<Recipe>

    @Query("SELECT * FROM ingredients_table WHERE recipe = :recipe")
    suspend fun getIngredients(recipe: Int): List<Ingredient>

    @Query("SELECT * FROM ingredients_table order by name asc")
    suspend fun getAnyIngredients(): List<Ingredient>

    @Query("DELETE FROM ingredients_table WHERE recipe = :recipeID")
    suspend fun deleteIngredientsFromRecipe(recipeID: Int) : Int
}