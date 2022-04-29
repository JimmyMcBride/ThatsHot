package com.stephan.lib_recipe.domain.use_cases.ingredient_use_cases

import com.stephan.lib_recipe.data.repository.Repository
import com.stephan.lib_recipe.domain.models.Ingredient

class GetIngredientsUseCase(private val repository: Repository){
    suspend operator fun invoke(recipe : Int): List<Ingredient> {
        return repository.getIngredients(recipe)
    }
}