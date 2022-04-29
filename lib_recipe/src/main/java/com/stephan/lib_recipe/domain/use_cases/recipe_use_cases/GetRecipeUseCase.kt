package com.stephan.lib_recipe.domain.use_cases.recipe_use_cases

import com.stephan.lib_recipe.data.repository.Repository
import com.stephan.lib_recipe.domain.models.Recipe

class GetRecipeUseCase (private val repository: Repository){
    suspend operator fun invoke(): List<Recipe> {
        return repository.getRecipes()
    }
}