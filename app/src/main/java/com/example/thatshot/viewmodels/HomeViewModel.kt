package com.example.thatshot.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stephan.lib_recipe.domain.models.Recipe
import com.stephan.lib_recipe.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    private var _allRecipes: MutableLiveData<List<Recipe>> = MutableLiveData()
    val allRecipes: LiveData<List<Recipe>> get() = _allRecipes

    fun getAllRecipes() = viewModelScope.launch(Dispatchers.Main) {
        _allRecipes.value = useCases.getRecipeUseCase()
    }
    fun addRecipe(recipe: Recipe) = viewModelScope.launch(Dispatchers.Main) {
        useCases.addRecipeUseCase(recipe)
    }
}