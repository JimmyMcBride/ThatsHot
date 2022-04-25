package com.example.thatshot.repo.implementation

import android.content.Context
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.repo.room.RecipeDatabase
import com.example.thatshot.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class RecipeImplement(context: Context): ThatsHotRepo {
    private val recipequerie = RecipeDatabase.getDatabaseInstance(context).recipeQueries()
    override suspend fun addRecipe(recipe: DummyRecipe) = withContext(Dispatchers.IO){
        recipequerie.addRecipe(recipe)
        }

    override suspend fun deleteRecipe(recipe: DummyRecipe) = withContext(Dispatchers.IO) {
        recipequerie.deleteRecipe(recipe)
    }

    override suspend fun grabAllRecipes(): Resource<List<DummyRecipe>> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = recipequerie.grabAllRecipes()
            Resource.Success(response)

        } catch (e: Exception){
            Resource.Error(e.localizedMessage ?: "Error 404")

        }
    }

    override suspend fun singleRecipe(id: Int): Resource<List<DummyRecipe>> = withContext(Dispatchers.IO){


        return@withContext try {
            val response = recipequerie.SingleRecipe(id)
            Resource.Success(response)

        } catch (e: Exception){
            val response = recipequerie.SingleRecipe(id)

            Resource.Error(e.localizedMessage ?: "Error 404")

        }
    }

}