package com.example.lib_recipes.repo

import com.example.lib_recipes.repo.models.DummyIngredient
import com.example.lib_recipes.repo.models.DummyRecipe
import com.example.lib_recipes.repo.models.relations.RecipeAndIngredients
import com.example.lib_recipes.util.Resource

interface ThatsHotRepo {
    suspend fun insertRecipe(recipe: DummyRecipe)

    suspend fun insertIngredient(ingredient: DummyIngredient)

    suspend fun deleteRecipe(recipe: DummyRecipe)

    suspend fun deleteIngredient(ingredient: DummyIngredient)

    suspend fun getRecipeAndIngredients(recipe: String): Resource<List<RecipeAndIngredients>>

    suspend fun getRecipes(): Resource<List<DummyRecipe>>

    suspend fun getIngredients(recipe: Int): Resource<List<DummyIngredient>>

    suspend fun getAnyIngredients(): Resource<List<DummyIngredient>>

    suspend fun deleteIngredientsFromRecipe(recipeID: Int) : Int

}

