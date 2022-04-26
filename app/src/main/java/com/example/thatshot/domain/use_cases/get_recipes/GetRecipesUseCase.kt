package com.example.thatshot.domain.use_cases.get_recipes

import com.example.thatshot.data.repository.Repository
import com.example.thatshot.domain.model.Recipe

class GetRecipesUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<Recipe> {
        return repository.getRecipes()
    }
}