package com.example.thatshot.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thatshot.repo.RepoImpl
import com.example.thatshot.viewmodels.EditRecipeViewModel

class ViewModelFactoryEditRecipe(
    private val repo: RepoImpl
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return EditRecipeViewModel(repo) as T
    }

}