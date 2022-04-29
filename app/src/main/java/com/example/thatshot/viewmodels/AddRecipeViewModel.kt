package com.example.thatshot.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stephan.lib_recipe.domain.models.Ingredient
import com.stephan.lib_recipe.domain.models.Recipe
import com.stephan.lib_recipe.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(private val useCases: UseCases): ViewModel() {
    var recipe: String = ""
    var description: String = ""

    var ingredientName: String = ""
    var ingredientUnit: String = ""
    var ingredientAmount: String = ""

    private var _saveBtn: MutableLiveData<Boolean> = MutableLiveData(false)
    val saveBtn: LiveData<Boolean> get() = _saveBtn

    private var _saveIngredient: MutableLiveData<Boolean> = MutableLiveData(false)
    val saveIngredient: LiveData<Boolean> get() = _saveIngredient

    private var _allRecipes: MutableLiveData<List<Recipe>> = MutableLiveData()
    val allRecipes: LiveData<List<Recipe>> get() = _allRecipes

    fun toggleSaveBtn(flag: Boolean){
        _saveBtn.value = flag
    }

    fun toggleIngredientBtn(flag: Boolean){
        _saveIngredient.value = flag
    }

    fun getAllRecipes() = viewModelScope.launch(Dispatchers.Main) {
        _allRecipes.value = useCases.getRecipeUseCase()

    }

    fun addRecipe(recipe: Recipe) = viewModelScope.launch(Dispatchers.Main) {
        useCases.addRecipeUseCase(recipe)
    }

    fun addIngredient(ingredient: Ingredient) = viewModelScope.launch(Dispatchers.Main) {
        useCases.insertIngredientUseCase(ingredient)
    }


}