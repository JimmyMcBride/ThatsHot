package com.example.thatshot.repo

import android.content.Context
import com.example.thatshot.repo.models.Recipe
import com.example.thatshot.util.StateResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ThatsHotRepoImplementation(context: Context): ThatsHotRepo {
    private val thatsHotDao = ThatsHotDatabase.getDatabaseInstance(context).thatsHotDao()

    override suspend fun addRecipe(item: Recipe) = withContext(Dispatchers.IO) {
        thatsHotDao.addRecipe(item)
    }

    override suspend fun fetchAllRecipes(): StateResource<MutableList<Recipe>> = withContext(Dispatchers.IO) {
        return@withContext try {
            val res = thatsHotDao.fetchAllRecipes()
            StateResource.Success(res)
        } catch (e: Exception) {
            StateResource.Error(e)
        }
    }

    override suspend fun deleteByID(id: Int) = withContext(Dispatchers.IO) {
        thatsHotDao.deleteByID(id)
    }

    override suspend fun update(item: Recipe) = withContext(Dispatchers.IO) {
        thatsHotDao.updateRecipe(item)
    }

    override suspend fun fetchByID(id: Int): StateResource<MutableList<Recipe>> = withContext(Dispatchers.IO) {
        return@withContext try {
            val res = thatsHotDao.fetchByID(id)
            StateResource.Success(res)
        } catch (e: Exception) {
            StateResource.Error(e)
        }
    }

    override suspend fun clearDB() = withContext(Dispatchers.IO) {
        thatsHotDao.clearDB()
    }
}