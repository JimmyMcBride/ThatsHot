package com.example.thatshot.domain.repository

import com.example.thatshot.domain.model.Recipe

interface LocalDataSource {
    suspend fun getRecipes(): List<Recipe>
}