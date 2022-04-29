package com.example.thatshot.domain.use_cases.cross_use_cases

import com.example.thatshot.data.repository.Repository

class DeleteIngFromRecipeUseCase(private val repository: Repository){
    suspend operator fun invoke(recipeID: Int) : Int{
        return repository.deleteIngredientsFromRecipe(recipeID)
    }
}