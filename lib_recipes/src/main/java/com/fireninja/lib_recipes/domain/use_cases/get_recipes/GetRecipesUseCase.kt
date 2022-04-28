package com.fireninja.lib_recipes.domain.use_cases.get_recipes

import com.fireninja.lib_recipes.data.repository.Repository
import com.fireninja.lib_recipes.domain.model.Recipe

class GetRecipesUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<Recipe> {
        return repository.getRecipes()
    }
}