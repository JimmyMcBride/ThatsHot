package com.example.thatshot.repo

import android.content.Context
import com.example.thatshot.repo.models.DummyIngredient
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.repo.room.DummyDatabase
import com.example.thatshot.util.RecipeAndIngredients
import com.example.thatshot.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoImpl(context: Context): ThatsHotRepo {

    private val dummyDao = DummyDatabase.getDatabaseInstance(context).dummyDao()

    override suspend fun insertRecipe(recipe: DummyRecipe) = withContext(Dispatchers.IO) {
        dummyDao.insertRecipe(recipe)
    }

    override suspend fun insertIngredient(ingredient: DummyIngredient) = withContext(Dispatchers.IO) {
        dummyDao.insertIngredient(ingredient)
    }

    override suspend fun deleteRecipe(recipe: DummyRecipe) = withContext(Dispatchers.IO) {
        dummyDao.deleteRecipe(recipe)
    }

    override suspend fun deleteIngredient(ingredient: DummyIngredient) = withContext(Dispatchers.IO) {
        dummyDao.deleteIngredient(ingredient)
    }

    override suspend fun getRecipeAndIngredients(recipe: String): Resource<List<RecipeAndIngredients>> = withContext(Dispatchers.IO) {
        return@withContext try {
        val response = dummyDao.getRecipeAndIngredients(recipe)
            Resource.Success(response)
        } catch (e: Exception){
            Resource.Error(e.localizedMessage ?: "Recipe and Ingredient not found")
        }
    }

    override suspend fun getRecipes(): Resource<List<DummyRecipe>> = withContext(Dispatchers.IO){
        return@withContext try {
            val response = dummyDao.getRecipes()
            Resource.Success(response)
        } catch (e: Exception){
            Resource.Error(e.localizedMessage ?: "Recipe and Ingredient not found")
        }
    }

    override suspend fun getIngredients(recipe: Int): Resource<List<DummyIngredient>> =
        withContext(Dispatchers.IO) {
        return@withContext try {
            val response = dummyDao.getIngredients(recipe)
            Resource.Success(response)
        } catch (e: Exception){
            Resource.Error(e.localizedMessage ?: "Ingredient not found")
        }
    }

    override suspend fun getAnyIngredients(): Resource<List<DummyIngredient>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = dummyDao.getAnyIngredients()
                Resource.Success(response)
            } catch (e: Exception){
                Resource.Error(e.localizedMessage ?: "Ingredient not found")
            }
        }

    override suspend fun deleteIngredientsFromRecipe(recipeID: Int): Int = withContext(Dispatchers.IO){
       dummyDao.deleteIngredientsInRecipe(recipeID)
    }
}