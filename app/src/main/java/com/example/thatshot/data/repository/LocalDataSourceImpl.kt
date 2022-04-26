package com.example.thatshot.data.repository

import com.example.thatshot.data.local.ThatsHotDatabase
import com.example.thatshot.domain.model.Recipe
import com.example.thatshot.domain.repository.LocalDataSource

class LocalDataSourceImpl(database: ThatsHotDatabase) : LocalDataSource {
    private val thatsHotDao = database.thatsHotDao()
    override suspend fun getRecipes(): List<Recipe> {
        return thatsHotDao.getRecipes()
    }
}