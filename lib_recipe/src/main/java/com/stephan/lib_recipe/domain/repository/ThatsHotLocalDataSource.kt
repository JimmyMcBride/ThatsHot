package com.stephan.lib_recipe.domain.repository

import com.stephan.lib_recipe.domain.models.Ingredient
import com.stephan.lib_recipe.domain.models.Recipe
import com.stephan.lib_recipe.util.RecipeAndIngredients


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
