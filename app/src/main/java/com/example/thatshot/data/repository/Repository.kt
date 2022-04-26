package com.example.thatshot.data.repository

import com.example.thatshot.domain.model.Recipe
import com.example.thatshot.domain.repository.LocalDataSource
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
