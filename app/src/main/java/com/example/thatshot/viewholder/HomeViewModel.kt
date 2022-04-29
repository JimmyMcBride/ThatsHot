package com.example.thatshot.viewholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_recipes.repo.RepoImpl
import com.example.lib_recipes.repo.models.DummyRecipe
import com.example.lib_recipes.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(val repo: com.example.lib_recipes.repo.RepoImpl): ViewModel() {

    private var _allRecipes: MutableLiveData<com.example.lib_recipes.util.Resource<List<com.example.lib_recipes.repo.models.DummyRecipe>>> = MutableLiveData(
        com.example.lib_recipes.util.Resource.Loading())
    val allRecipes: LiveData<com.example.lib_recipes.util.Resource<List<com.example.lib_recipes.repo.models.DummyRecipe>>> get() = _allRecipes

    fun getAllRecipes() = viewModelScope.launch(Dispatchers.Main) {
        val response = repo.getRecipes()
        _allRecipes.value = response
    }
}