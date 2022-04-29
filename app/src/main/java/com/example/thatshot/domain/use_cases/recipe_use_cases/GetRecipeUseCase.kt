package com.example.thatshot.domain.use_cases.recipe_use_cases

import com.example.thatshot.data.repository.Repository
import com.example.thatshot.domain.models.Recipe

class GetRecipeUseCase (private val repository: Repository){
    suspend operator fun invoke(): List<Recipe> {
        return repository.getRecipes()
    }
}