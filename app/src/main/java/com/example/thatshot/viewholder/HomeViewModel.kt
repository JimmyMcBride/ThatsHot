package com.example.thatshot.viewholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.repo.RepoImpl
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(val repo: RepoImpl): ViewModel() {

    private var _allRecipes: MutableLiveData<Resource<List<DummyRecipe>>> = MutableLiveData(Resource.Loading())
    val allRecipes: LiveData<Resource<List<DummyRecipe>>> get() = _allRecipes

    fun getAllRecipes() = viewModelScope.launch(Dispatchers.Main) {
        val response = repo.getRecipes()
        _allRecipes.value = response
    }
}