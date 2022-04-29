package com.example.thatshot.domain.use_cases.ingredient_use_cases

import com.example.thatshot.data.repository.Repository
import com.example.thatshot.domain.models.Ingredient

class GetIngredientsUseCase(private val repository: Repository){
    suspend operator fun invoke(recipe : Int): List<Ingredient> {
        return repository.getIngredients(recipe)
    }
}