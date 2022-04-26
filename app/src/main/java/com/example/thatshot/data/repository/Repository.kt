package com.example.thatshot.data.repository

import com.example.thatshot.domain.model.Recipe
import com.example.thatshot.domain.repository.LocalDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource
) {
    suspend fun getRecipes(): List<Recipe> {
        return local.getRecipes()
    }
}
