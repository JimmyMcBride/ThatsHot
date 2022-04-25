package com.example.thatshot.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.repo.RepoImpl
import com.example.thatshot.repo.models.DummyRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewRecipeViewModel(val repo: RepoImpl) : ViewModel() {

    private var _recipe = MutableLiveData<DummyRecipe>()
    val recipe: LiveData<DummyRecipe> get() = _recipe

}