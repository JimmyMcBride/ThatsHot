package com.fireninja.lib_recipes.domain.repository

import com.fireninja.lib_recipes.domain.model.Recipe

interface LocalDataSource {
    suspend fun getRecipes(): List<Recipe>
}