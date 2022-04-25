package com.example.thatshot.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thatshot.viewmodels.RecipeListViewmodel
import com.example.thatshot.repo.implementation.RecipeImplement
import com.example.thatshot.viewmodels.SingleRecipeViewmodel

class ViewModelFactroySingle(
    private val recipe: RecipeImplement
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SingleRecipeViewmodel(recipe) as T
    }
}