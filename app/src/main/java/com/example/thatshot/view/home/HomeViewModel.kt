package com.example.thatshot.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.domain.model.Recipe
import com.example.thatshot.domain.use_cases.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _recipes: MutableStateFlow<List<Recipe>> = MutableStateFlow(mutableListOf())
    val recipes: StateFlow<List<Recipe>> = _recipes

    fun getRecipes() = viewModelScope.launch {
        _recipes.value = useCases.getRecipesUseCase()
    }
}