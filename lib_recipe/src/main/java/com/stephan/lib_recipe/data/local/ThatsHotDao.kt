package com.stephan.lib_recipe.data.local

import androidx.room.*
import com.stephan.lib_recipe.domain.models.Ingredient
import com.stephan.lib_recipe.domain.models.Recipe
import com.stephan.lib_recipe.util.RecipeAndIngredients

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