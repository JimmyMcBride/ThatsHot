package com.example.thatshot.domain.use_cases.recipe_use_cases

import com.example.thatshot.data.repository.Repository
import com.example.thatshot.domain.models.Recipe

class AddRecipeUseCase(private val repository: Repository){
    suspend operator fun invoke(recipe: Recipe) {
        return repository.addRecipe(recipe)
    }
}