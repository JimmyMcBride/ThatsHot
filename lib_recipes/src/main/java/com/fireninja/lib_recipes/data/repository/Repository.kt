package com.fireninja.lib_recipes.data.repository

import com.fireninja.lib_recipes.domain.model.Recipe
import com.fireninja.lib_recipes.domain.repository.LocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource
) {
    suspend fun getRecipes(): List<Recipe> = withContext(Dispatchers.IO) {
        return@withContext local.getRecipes()
    }
}
