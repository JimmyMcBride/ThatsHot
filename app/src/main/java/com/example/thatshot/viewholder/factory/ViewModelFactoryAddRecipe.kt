package com.example.thatshot.viewholder.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lib_recipes.repo.RepoImpl
import com.example.thatshot.viewholder.AddRecipeViewModel

class ViewModelFactoryAddRecipe(
    private val repo: com.example.lib_recipes.repo.RepoImpl
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddRecipeViewModel(repo) as T
    }
}