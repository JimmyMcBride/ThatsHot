package com.example.thatshot.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.model.models.Recipe
import com.example.thatshot.model.repo.RepoImpl
import com.example.thatshot.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(val repo: RepoImpl): ViewModel() {

    private var _recipes: MutableLiveData<Resource<List<Recipe>>> = MutableLiveData(Resource.Loading())
    val recipes: LiveData<Resource<List<Recipe>>> get() = _recipes


    fun getAllRecipes() {
        viewModelScope.launch(Dispatchers.Main) {
            _recipes.value = repo.getAllRecipes()
        }
    }

    fun initData() {
        viewModelScope.launch(Dispatchers.Main) {
            when (val response = repo.countRecipes()) {
                is Resource.Success -> {
                    if (response.data <= 0) loadDummyData()
                }
                is Resource.Loading -> {
                    return@launch
                }
                is Resource.Error -> {
                    return@launch
                }
            }
        }
    }

    private fun loadDummyData() {
            val burger = Recipe(
                name = "Hamburger",
                description = "Adequate any time."
            )
            val hotdog = Recipe (
                name = "Hotdog",
                description = "Staple for ball games."
            )
            viewModelScope.launch(Dispatchers.Main) {
                repo.addEditRecipe(burger)
                repo.addEditRecipe(hotdog)
            }

    }
}