package com.stephan.lib_recipe.domain.use_cases.cross_use_cases

import com.stephan.lib_recipe.data.repository.Repository
import com.stephan.lib_recipe.util.RecipeAndIngredients

class GetRecipeAndIngredientUseCase(private val repository: Repository) {
    suspend operator fun invoke(recipe: String): List<RecipeAndIngredients> {
        return repository.getRecipeAndIngredients(recipe)
    }
}