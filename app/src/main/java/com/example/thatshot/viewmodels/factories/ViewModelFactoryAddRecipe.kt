package com.example.thatshot.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thatshot.model.repo.RepoImpl
import com.example.thatshot.viewmodels.AddRecipeViewModel

class ViewModelFactoryAddRecipe(
    private val repo: RepoImpl
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddRecipeViewModel(repo) as T
    }
}