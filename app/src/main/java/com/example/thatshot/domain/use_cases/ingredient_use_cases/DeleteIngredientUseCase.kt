package com.example.thatshot.domain.use_cases.ingredient_use_cases

import com.example.thatshot.data.repository.Repository
import com.example.thatshot.domain.models.Ingredient

class DeleteIngredientUseCase(private val repository: Repository){
    suspend operator fun invoke(ingredient: Ingredient) {
        return repository.deleteIngredient(ingredient)
    }
}