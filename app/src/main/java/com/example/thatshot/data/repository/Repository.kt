package com.example.thatshot.data.repository

import com.example.thatshot.domain.models.Ingredient
import com.example.thatshot.domain.models.Recipe
import com.example.thatshot.domain.repository.ThatsHotLocalDataSource
import com.example.thatshot.util.RecipeAndIngredients
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(private val local : ThatsHotLocalDataSource){
    suspend fun getRecipes() : List<Recipe> = withContext(Dispatchers.IO) {
        return@withContext local.getRecipes()
    }
    suspend fun addRecipe(recipe: Recipe) = withContext(Dispatchers.IO) {
        return@withContext local.addRecipe(recipe)
    }
    suspend fun deleteRecipe(recipe: Recipe) = withContext(Dispatchers.IO) {
        return@withContext local.deleteRecipe(recipe)
    }
    suspend fun deleteIngredient(ingredient: Ingredient) = withContext(Dispatchers.IO) {
        return@withContext local.deleteIngredient(ingredient)
    }
    suspend fun getIngredients(recipe: Int): List<Ingredient> = withContext(Dispatchers.IO){
        return@withContext local.getIngredients(recipe)
    }
    suspend fun insertIngredient(ingredient: Ingredient) = withContext(Dispatchers.IO) {
        return@withContext local.insertIngredient(ingredient)
    }
    suspend fun getRecipeAndIngredients(recipe: String): List<RecipeAndIngredients> = withContext(Dispatchers.IO){
        return@withContext local.getRecipeAndIngredients(recipe)
    }
    suspend fun getAnyIngredients(): List<Ingredient> = withContext(Dispatchers.IO){
        return@withContext local.getAnyIngredients()
    }
    suspend fun deleteIngredientsFromRecipe(recipeID: Int): Int = withContext(Dispatchers.IO){
        return@withContext local.deleteIngredientsFromRecipe(recipeID)
    }
}