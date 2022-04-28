package com.fireninja.lib_recipes.data.repository

import com.fireninja.lib_recipes.data.local.ThatsHotDatabase
import com.fireninja.lib_recipes.domain.model.Recipe
import com.fireninja.lib_recipes.domain.repository.LocalDataSource

class LocalDataSourceImpl(database: ThatsHotDatabase) : LocalDataSource {
    private val thatsHotDao = database.thatsHotDao()
    override suspend fun getRecipes(): List<Recipe> {
        return thatsHotDao.getRecipes()
    }
}