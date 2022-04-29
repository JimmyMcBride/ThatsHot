package com.example.thatshot.viewholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lib_recipes.repo.RepoImpl
import com.example.lib_recipes.repo.models.DummyIngredient
import com.example.lib_recipes.repo.models.DummyRecipe
import com.example.lib_recipes.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddRecipeViewModel(val repo: com.example.lib_recipes.repo.RepoImpl): ViewModel() {
    var recipe: String = ""
    var description: String = ""

    var ingredientName: String = ""
    var ingredientUnit: String = ""
    var ingredientAmount: String = ""

    private var _saveBtn: MutableLiveData<Boolean> = MutableLiveData(false)
    val saveBtn: LiveData<Boolean> get() = _saveBtn

    private var _saveIngredient: MutableLiveData<Boolean> = MutableLiveData(false)
    val saveIngredient: LiveData<Boolean> get() = _saveIngredient

    private var _allRecipes: MutableLiveData<com.example.lib_recipes.util.Resource<List<com.example.lib_recipes.repo.models.DummyRecipe>>> = MutableLiveData(
        com.example.lib_recipes.util.Resource.Loading())
    val allRecipes: LiveData<com.example.lib_recipes.util.Resource<List<com.example.lib_recipes.repo.models.DummyRecipe>>> get() = _allRecipes

    fun toggleSaveBtn(flag: Boolean){
        _saveBtn.value = flag
    }

    fun toggleIngredientBtn(flag: Boolean){
        _saveIngredient.value = flag
    }

    fun getAllRecipes() = viewModelScope.launch(Dispatchers.Main) {
        val response = repo.getRecipes()
        _allRecipes.value = response
    }

    fun addRecipe(recipe: com.example.lib_recipes.repo.models.DummyRecipe) = viewModelScope.launch(Dispatchers.Main) {
        repo.insertRecipe(recipe)
    }

    fun addIngredient(ingredient: com.example.lib_recipes.repo.models.DummyIngredient) = viewModelScope.launch(Dispatchers.Main) {
        repo.insertIngredient(ingredient)
    }


}