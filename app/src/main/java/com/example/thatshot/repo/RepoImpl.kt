package com.example.thatshot.repo

import android.content.Context
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.repo.room.RecipeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoImpl(context: Context): Repo {

    private val recipeDAO = RecipeDatabase.getDatabaseInstance(context).recipeDAO()

    override suspend fun addRecipe(recipe: DummyRecipe) = withContext(Dispatchers.IO) {
        recipeDAO.addRecipeEntry(recipe)
    }

    override suspend fun editRecipe(recipe: DummyRecipe) = withContext(Dispatchers.IO) {
        recipeDAO.addRecipeEntry(recipe)
    }

    override suspend fun deleteRecipe(recipe: DummyRecipe) = withContext(Dispatchers.IO) {
        recipeDAO.deleteRecipe(recipe)
    }

    override suspend fun grabAllRecipes(): List<DummyRecipe> = withContext(Dispatchers.IO) {
        return@withContext recipeDAO.grabAllRecipes()
    }

}