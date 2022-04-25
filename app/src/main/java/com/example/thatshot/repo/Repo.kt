package com.example.thatshot.repo

import com.example.thatshot.repo.models.DummyRecipe

interface Repo {
    suspend fun addRecipe(recipe: DummyRecipe)
    suspend fun editRecipe(recipe: DummyRecipe)
    suspend fun deleteRecipe(recipe: DummyRecipe)
    suspend fun grabAllRecipes(): List<DummyRecipe>
}