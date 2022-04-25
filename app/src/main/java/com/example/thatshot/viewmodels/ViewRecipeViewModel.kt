package com.example.thatshot.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.model.models.Ingredient
import com.example.thatshot.model.models.Recipe
import com.example.thatshot.model.repo.RepoImpl
import com.example.thatshot.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewRecipeViewModel(val repo: RepoImpl): ViewModel() {

    private var _ingredients: MutableLiveData<Resource<List<Ingredient>>> = MutableLiveData(Resource.Loading())
    val ingredients: LiveData<Resource<List<Ingredient>>> get() = _ingredients

    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.Main) {
            repo.deleteRecipe(recipe)
        }
    }

    fun getRecipeIngredients(recipeId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _ingredients.value = repo.getRecipeIngredients(recipeId)
        }
    }
}