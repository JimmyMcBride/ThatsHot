package com.example.thatshot.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.model.models.Recipe
import com.example.thatshot.model.repo.RepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddRecipeViewModel(private val repo: RepoImpl): ViewModel() {

    fun addRecipe(recipe: Recipe){
        viewModelScope.launch(Dispatchers.Main){
            repo.addEditRecipe(recipe)
        }
    }
 }