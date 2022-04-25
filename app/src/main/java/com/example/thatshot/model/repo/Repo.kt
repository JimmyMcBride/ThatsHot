package com.example.thatshot.model.repo

import com.example.thatshot.model.models.Ingredient
import com.example.thatshot.model.models.Recipe
import com.example.thatshot.util.Resource

interface Repo {
    suspend fun addEditIngredient(ingredient: Ingredient)
    suspend fun getRecipeIngredients(recipeId: Int): Resource<List<Ingredient>>
    suspend fun deleteIngredient(ingredient: Ingredient)

    suspend fun addEditRecipe(recipe: Recipe)
    suspend fun updateRecipeName(id: Int, name: String)
    suspend fun updateRecipeDescription(id: Int, description: String)
    suspend fun getAllRecipes(): Resource<List<Recipe>>
    suspend fun deleteRecipe(recipe: Recipe)
    suspend fun countRecipes() : Resource<Int>
}