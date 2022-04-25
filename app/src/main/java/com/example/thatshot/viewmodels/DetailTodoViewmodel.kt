package com.example.thatshot.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.repo.implementation.RecipeImplement
import com.example.thatshot.repo.models.DummyRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailRecipeViewmodel(val repo: RecipeImplement) : ViewModel() {
    var name: String =""
    var description : String = ""
    private val _continueButton = MutableLiveData<Boolean>(false)
    val continueButton : LiveData<Boolean> get() = _continueButton

    fun setContinueButtonValue(flag: Boolean){
        _continueButton.value = flag
    }
    fun addTodoItem(recipe: DummyRecipe) = viewModelScope.launch(Dispatchers.Main){
        repo.addRecipe(recipe)

    }
}