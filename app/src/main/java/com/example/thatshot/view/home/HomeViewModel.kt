package com.example.thatshot.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fireninja.lib_recipes.domain.model.Recipe
import com.fireninja.lib_recipes.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: com.fireninja.lib_recipes.domain.use_cases.UseCases
) : ViewModel() {
    private val _recipes: MutableLiveData<List<com.fireninja.lib_recipes.domain.model.Recipe>> = MutableLiveData(mutableListOf())
    val recipes: LiveData<List<com.fireninja.lib_recipes.domain.model.Recipe>> = _recipes

    fun getRecipes() = viewModelScope.launch(Dispatchers.Main) {
        _recipes.value = useCases.getRecipesUseCase()
    }
}