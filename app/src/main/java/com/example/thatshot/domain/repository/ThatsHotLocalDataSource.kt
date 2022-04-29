package com.example.thatshot.domain.repository

import com.example.thatshot.domain.models.Ingredient
import com.example.thatshot.domain.models.Recipe
import com.example.thatshot.util.RecipeAndIngredients

interface ThatsHotLocalDataSource {
    suspend fun addRecipe(recipe: Recipe)

    suspend fun insertIngredient(ingredient: Ingredient)

    suspend fun deleteRecipe(recipe: Recipe)

    suspend fun deleteIngredient(ingredient: Ingredient)

    suspend fun getRecipeAndIngredients(recipe: String): List<RecipeAndIngredients>

    suspend fun getRecipes(): List<Recipe>

    suspend fun getIngredients(recipe: Int): List<Ingredient>

    suspend fun getAnyIngredients(): List<Ingredient>

    suspend fun deleteIngredientsFromRecipe(recipeID: Int): Int
}
