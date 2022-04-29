package com.example.thatshot.viewholder

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_recipes.repo.RepoImpl
import com.example.lib_recipes.repo.models.DummyIngredient
import com.example.lib_recipes.repo.models.DummyRecipe
import com.example.lib_recipes.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

class ViewRecipeViewModel(val repo: com.example.lib_recipes.repo.RepoImpl): ViewModel() {

    private var _allIngredients: MutableLiveData<com.example.lib_recipes.util.Resource<List<com.example.lib_recipes.repo.models.DummyIngredient>>> = MutableLiveData(
        com.example.lib_recipes.util.Resource.Loading())
    val allIngredients: LiveData<com.example.lib_recipes.util.Resource<List<com.example.lib_recipes.repo.models.DummyIngredient>>> get() = _allIngredients

    fun deleteRecipe(recipe: com.example.lib_recipes.repo.models.DummyRecipe) = viewModelScope.launch(Dispatchers.Main){
        repo.deleteRecipe(recipe)
    }
    fun deleteIngredients(recipeID: Int) = viewModelScope.launch(Dispatchers.Main){
       repo.deleteIngredientsFromRecipe(recipeID)
    }

    fun getIngredients(recipeID: Int) = viewModelScope.launch(Dispatchers.Main) {
        val response = repo.getIngredients(recipeID)
        _allIngredients.value = response
    }

}