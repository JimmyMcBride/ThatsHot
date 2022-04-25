package com.example.thatshot.repo

import com.example.thatshot.repo.models.DummyIngredient
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.util.RecipeAndIngredients
import com.example.thatshot.util.Resource

interface ThatsHotRepo {
    suspend fun insertRecipe(recipe: DummyRecipe)

    suspend fun insertIngredient(ingredient: DummyIngredient)

    suspend fun deleteRecipe(recipe: DummyRecipe)

    suspend fun deleteIngredient(ingredient: DummyIngredient)

    suspend fun getRecipeAndIngredients(recipe: String): Resource<List<RecipeAndIngredients>>

    suspend fun getRecipes(): Resource<List<DummyRecipe>>

    suspend fun getIngredients(recipe: Int): Resource<List<DummyIngredient>>

    suspend fun getAnyIngredients(): Resource<List<DummyIngredient>>

    suspend fun deleteIngredientsFromRecipe(recipeID: Int): Int
}
