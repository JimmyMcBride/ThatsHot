package com.example.thatshot.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thatshot.viewmodels.RecipeListViewmodel
import com.example.thatshot.repo.implementation.RecipeImplement

class ViewModelFactroyRecipeList(
    private val recipe: RecipeImplement
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeListViewmodel(recipe) as T
    }
}