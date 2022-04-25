package com.example.thatshot.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.repo.implementation.RecipeImplement
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeListViewmodel(val repo: RecipeImplement) : ViewModel() {
private val _todos = MutableLiveData<Resource<List<DummyRecipe>>>(Resource.Loading())
     val todos: LiveData<Resource<List<DummyRecipe>>> get() = _todos

    fun grabAllRecipes() = viewModelScope.launch(Dispatchers.Main){
        val response = repo.grabAllRecipes()
        _todos.value = response

    }
    fun deleteRecipe(recipe: DummyRecipe) = viewModelScope.launch(Dispatchers.Main) {
        repo.deleteRecipe(recipe)
    }
}