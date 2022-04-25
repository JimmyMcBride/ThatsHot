package com.example.thatshot.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thatshot.viewmodels.CreateRecipeViewmodel
import com.example.thatshot.repo.implementation.RecipeImplement
var name: String =""
var description : String = ""
class ViewModelFactoryCreate (
    private val recipe:RecipeImplement) : ViewModelProvider.NewInstanceFactory(){

    override fun<T : ViewModel> create(modelClass: Class<T>):T{
        return CreateRecipeViewmodel(recipe) as T
    }
}