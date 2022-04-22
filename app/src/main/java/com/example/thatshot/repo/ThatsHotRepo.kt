package com.example.thatshot.repo

import com.example.thatshot.repo.models.Recipe
import com.example.thatshot.util.StateResource

interface ThatsHotRepo {
    suspend fun addRecipe(item: Recipe)
    suspend fun fetchAllRecipes(): StateResource<MutableList<Recipe>>
    suspend fun deleteByID(id: Int)
    suspend fun update(item: Recipe)
    suspend fun fetchByID(id: Int): StateResource<MutableList<Recipe>>
    suspend fun clearDB()
}