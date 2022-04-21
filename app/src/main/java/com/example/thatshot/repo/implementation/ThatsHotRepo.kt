package com.example.thatshot.repo.implementation

import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.utils.Resource

interface ThatsHotRepo{
    suspend fun addRecipe(recipe : DummyRecipe)
    suspend fun deleteRecipe(recipe : DummyRecipe)
    suspend fun grabAllRecipes(): Resource<List<DummyRecipe>>
    suspend fun singleRecipe(id: Int) : Resource<List<DummyRecipe>>
    //    fun SingleRecipe(id: String): List<DummyRecipe>


}