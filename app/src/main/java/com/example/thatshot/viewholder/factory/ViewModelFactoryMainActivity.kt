package com.example.thatshot.viewholder.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thatshot.repo.RepoImpl
import com.example.thatshot.viewholder.AddRecipeViewModel
import com.example.thatshot.viewholder.MainActivityViewModel

class ViewModelFactoryMainActivity(
    private val repo: RepoImpl
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(repo) as T
    }
}