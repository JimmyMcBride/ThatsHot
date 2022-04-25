package com.example.thatshot.repo

import android.content.Context
import com.example.thatshot.local.ThatsHotDatabase
import com.example.thatshot.repo.models.DummyIngredient
import com.example.thatshot.repo.models.DummyRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ThatsHotRepo(context: Context) {

    private val thatsHotDao = ThatsHotDatabase.getInstance(context).thatsHotDao()


    suspend fun getRecipes() = withContext(Dispatchers.IO) {
        val savedRecipies: List<DummyRecipe> = thatsHotDao.getAll()

        return@withContext savedRecipies.ifEmpty {
            val recipes: List<DummyRecipe> = thatsHotDao.getAll()
            val recipe: List<DummyRecipe> = recipes.map {
                DummyRecipe(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    //ingredients = it.ingredients
                )
            }
            thatsHotDao.insert(recipe)
            return@ifEmpty recipe
        }
    }
}