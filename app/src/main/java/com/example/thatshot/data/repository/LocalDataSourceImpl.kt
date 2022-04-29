package com.example.thatshot.data.repository

import com.example.thatshot.data.local.ThatsHotDatabase
import com.example.thatshot.domain.models.Ingredient
import com.example.thatshot.domain.models.Recipe
import com.example.thatshot.domain.repository.ThatsHotLocalDataSource
import com.example.thatshot.util.RecipeAndIngredients
import javax.inject.Inject


class LocalDataSourceImpl @Inject constructor(database: ThatsHotDatabase): ThatsHotLocalDataSource {

    private val thatsHotDao = database.thatsHotDao()

    override suspend fun addRecipe(recipe: Recipe)  {
        return thatsHotDao.addRecipe(recipe)
    }

    override suspend fun insertIngredient(ingredient: Ingredient)  {
        return thatsHotDao.insertIngredient(ingredient)
    }

    override suspend fun deleteRecipe(recipe: Recipe)  {
        return thatsHotDao.deleteRecipe(recipe)
    }

    override suspend fun deleteIngredient(ingredient: Ingredient) {
        return thatsHotDao.deleteIngredient(ingredient)
    }

    override suspend fun getRecipeAndIngredients(recipe: String): List<RecipeAndIngredients> {
        return thatsHotDao.getRecipeAndIngredients(recipe)
    }

    override suspend fun getRecipes(): List<Recipe> {
        return thatsHotDao.getRecipes()
    }

    override suspend fun getIngredients(recipe: Int): List<Ingredient>{
        return thatsHotDao.getIngredients(recipe)
    }

    override suspend fun getAnyIngredients(): List<Ingredient> {
        return thatsHotDao.getAnyIngredients()
    }

    override suspend fun deleteIngredientsFromRecipe(recipeID: Int): Int {
       return thatsHotDao.deleteIngredientsFromRecipe(recipeID)
    }
}