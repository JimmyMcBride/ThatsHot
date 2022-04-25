package com.example.thatshot.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.repo.RepoImpl
import com.example.thatshot.repo.models.DummyRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditRecipeViewModel(val repo: RepoImpl) : ViewModel() {

    private var _recipes = MutableLiveData<List<DummyRecipe>>()
    val recipes: LiveData<List<DummyRecipe>> get() = _recipes

    fun editRecipeEntry(recipe: DummyRecipe) = viewModelScope.launch(Dispatchers.Main) {
        repo.editRecipe(recipe)
    }

}