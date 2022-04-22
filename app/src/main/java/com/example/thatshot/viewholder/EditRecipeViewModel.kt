package com.example.thatshot.viewholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.repo.RepoImpl
import com.example.thatshot.repo.models.DummyIngredient
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditRecipeViewModel(val repo: RepoImpl): ViewModel() {

    private var _allIngredients: MutableLiveData<Resource<List<DummyIngredient>>> = MutableLiveData(
        Resource.Loading())
    val allIngredients: LiveData<Resource<List<DummyIngredient>>> get() = _allIngredients

    fun addRecipe(recipe: DummyRecipe) = viewModelScope.launch(Dispatchers.Main) {
        repo.insertRecipe(recipe)
    }
    fun getIngredients(recipeID: Int) = viewModelScope.launch(Dispatchers.Main) {
        val response = repo.getIngredients(recipeID)
        _allIngredients.value = response
    }
}