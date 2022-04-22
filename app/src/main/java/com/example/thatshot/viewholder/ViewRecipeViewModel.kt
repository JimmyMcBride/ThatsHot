package com.example.thatshot.viewholder

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.repo.RepoImpl
import com.example.thatshot.repo.models.DummyIngredient
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

class ViewRecipeViewModel(val repo: RepoImpl): ViewModel() {

    private var _allIngredients: MutableLiveData<Resource<List<DummyIngredient>>> = MutableLiveData(Resource.Loading())
    val allIngredients: LiveData<Resource<List<DummyIngredient>>> get() = _allIngredients

    fun deleteRecipe(recipe: DummyRecipe) = viewModelScope.launch(Dispatchers.Main){
        repo.deleteRecipe(recipe)
    }
    fun deleteIngredients(recipeID: Int) = viewModelScope.launch(Dispatchers.Main){
       repo.deleteIngredientsFromRecipe(recipeID)
    }

    fun getIngredients(recipeID: Int) = viewModelScope.launch(Dispatchers.Main) {
        val response = repo.getIngredients(recipeID)
        _allIngredients.value = response
    }

}