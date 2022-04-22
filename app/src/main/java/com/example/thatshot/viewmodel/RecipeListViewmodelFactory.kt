package com.example.thatshot.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thatshot.repo.ThatsHotRepoImplementation

class RecipeListViewmodelFactory(private val repo: ThatsHotRepoImplementation) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeListViewModel(repo) as T
    }
}