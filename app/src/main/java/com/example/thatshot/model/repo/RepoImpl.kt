package com.example.thatshot.model.repo

import android.content.Context
import com.example.thatshot.model.models.Ingredient
import com.example.thatshot.model.models.Recipe
import com.example.thatshot.model.room.JimmysCookbookDatabase
import com.example.thatshot.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class RepoImpl(context: Context): Repo {

    private val ingredientDao = JimmysCookbookDatabase.getDatabaseInstance(context).ingredientDao()
    private val recipeDao = JimmysCookbookDatabase.getDatabaseInstance(context).recipeDao()

    override suspend fun addEditIngredient(ingredient: Ingredient) = withContext(Dispatchers.IO){
        ingredientDao.addEditIngredient(ingredient)
    }

    override suspend fun getRecipeIngredients(recipeId: Int): Resource<List<Ingredient>> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = ingredientDao.getRecipeIngredients(recipeId)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Database connection error")
        }
    }

    override suspend fun deleteIngredient(ingredient: Ingredient) {
        ingredientDao.deleteIngredient(ingredient)
    }

    override suspend fun addEditRecipe(recipe: Recipe) = withContext(Dispatchers.IO) {
        recipeDao.addEditRecipe(recipe)
    }

    override suspend fun getAllRecipes(): Resource<List<Recipe>> = withContext(Dispatchers.IO){
        return@withContext try {
            val response = recipeDao.getAllRecipes()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Database connection failed")
        }
    }

    override suspend fun updateRecipeName(id: Int, name: String)  = withContext(Dispatchers.IO) {
        recipeDao.updateRecipeName(id, name)
    }

    override suspend fun updateRecipeDescription(id: Int, description: String) {
        recipeDao.updateRecipeDescription(id, description)
    }

    override suspend fun deleteRecipe(recipe: Recipe)  = withContext(Dispatchers.IO) {
        recipeDao.deleteRecipe(recipe)
    }

    override suspend fun countRecipes(): Resource<Int> = withContext(Dispatchers.IO){
        return@withContext try {
            val response = recipeDao.countRecipes()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Database connection failed")
        }
    }


}