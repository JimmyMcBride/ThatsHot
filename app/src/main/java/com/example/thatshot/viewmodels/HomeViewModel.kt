package com.example.thatshot.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.repo.RepoImpl
import com.example.thatshot.repo.models.DummyRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(val repo: RepoImpl) : ViewModel() {

    private var _recipes = MutableLiveData<List<DummyRecipe>>()
    val recipes: LiveData<List<DummyRecipe>> get() = _recipes

    fun getRecipeList() = viewModelScope.launch(Dispatchers.Main) {
        val response = repo.grabAllRecipes()
        _recipes.value = response
    }

}