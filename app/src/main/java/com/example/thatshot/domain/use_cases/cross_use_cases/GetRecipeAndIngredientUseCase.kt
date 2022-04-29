package com.example.thatshot.domain.use_cases.cross_use_cases

import com.example.thatshot.data.repository.Repository
import com.example.thatshot.util.RecipeAndIngredients

class GetRecipeAndIngredientUseCase(private val repository: Repository) {
    suspend operator fun invoke(recipe: String): List<RecipeAndIngredients> {
        return repository.getRecipeAndIngredients(recipe)
    }
}