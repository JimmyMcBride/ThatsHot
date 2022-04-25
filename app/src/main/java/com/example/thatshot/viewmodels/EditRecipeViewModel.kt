package com.example.thatshot.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.model.models.Recipe
import com.example.thatshot.model.repo.RepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditRecipeViewModel(
    private val repo: RepoImpl
): ViewModel() {

    fun updateRecipeName(id: Int, name: String) {
        viewModelScope.launch(Dispatchers.Main) {
            repo.updateRecipeName(id,name)
        }
    }

    fun updateRecipeDescription(id: Int, description: String) {
        viewModelScope.launch(Dispatchers.Main) {
            repo.updateRecipeDescription(id, description)
        }
    }
}