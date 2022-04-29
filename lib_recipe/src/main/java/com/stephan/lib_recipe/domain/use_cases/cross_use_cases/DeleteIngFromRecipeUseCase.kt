package com.stephan.lib_recipe.domain.use_cases.cross_use_cases

import com.stephan.lib_recipe.data.repository.Repository

class DeleteIngFromRecipeUseCase(private val repository: Repository){
    suspend operator fun invoke(recipeID: Int) : Int{
        return repository.deleteIngredientsFromRecipe(recipeID)
    }
}