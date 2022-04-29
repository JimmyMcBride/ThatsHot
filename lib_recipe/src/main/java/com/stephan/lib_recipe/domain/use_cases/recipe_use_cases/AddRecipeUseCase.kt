package com.stephan.lib_recipe.domain.use_cases.recipe_use_cases

import com.stephan.lib_recipe.data.repository.Repository
import com.stephan.lib_recipe.domain.models.Recipe

class AddRecipeUseCase(private val repository: Repository){
    suspend operator fun invoke(recipe: Recipe) {
        return repository.addRecipe(recipe)
    }
}