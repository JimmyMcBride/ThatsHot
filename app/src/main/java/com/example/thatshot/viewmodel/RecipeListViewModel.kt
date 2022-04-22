package com.example.thatshot.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.repo.ThatsHotRepoImplementation
import com.example.thatshot.repo.models.Recipe
import com.example.thatshot.util.StateResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeListViewModel(private val repo: ThatsHotRepoImplementation): ViewModel() {
    private var _state: MutableLiveData<StateResource<MutableList<Recipe>>> = MutableLiveData(StateResource.Standby())
    val state: LiveData<StateResource<MutableList<Recipe>>> get() = _state

    private fun setLoading() {
        _state.value = StateResource.Loading()
    }
    fun setStandby() {
        _state.value = StateResource.Standby()
    }

    fun addRecipe(item: Recipe) = viewModelScope.launch(Dispatchers.Main) {
        setLoading()
        repo.addRecipe(item)
        _state.value = repo.fetchAllRecipes()
    }

    fun deleteByID(id: Int) = viewModelScope.launch(Dispatchers.Main) {
        setLoading()
        repo.deleteByID(id)
        _state.value = repo.fetchAllRecipes()
    }

    fun fetchRecipes() = viewModelScope.launch(Dispatchers.Main) {
        setLoading()
        _state.value = repo.fetchAllRecipes()
    }

    fun update(item: Recipe) = viewModelScope.launch(Dispatchers.Main) {
        setLoading()
        repo.update(item)
        _state.value = repo.fetchAllRecipes()
    }

    fun fetchByID(id: Int) = viewModelScope.launch(Dispatchers.Main) {
        setLoading()
        _state.value = repo.fetchByID(id)
    }
}