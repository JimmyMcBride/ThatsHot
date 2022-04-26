package com.example.thatshot.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.data.local.ThatsHotDatabase
import com.example.thatshot.data.repository.LocalDataSourceImpl
import com.example.thatshot.domain.model.Recipe
import com.example.thatshot.domain.repository.LocalDataSource
import com.example.thatshot.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _recipes: MutableLiveData<List<Recipe>> = MutableLiveData(mutableListOf())
    val recipes: LiveData<List<Recipe>> = _recipes

    fun getRecipes() = viewModelScope.launch(Dispatchers.Main) {
        _recipes.value = useCases.getRecipesUseCase()
    }
}