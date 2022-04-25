package com.example.thatshot.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatshot.repo.implementation.RecipeImplement
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SingleRecipeViewmodel(val repo: RecipeImplement) : ViewModel() {
    var name: String =""
    var description : String = ""
    private val _todo = MutableLiveData<List<DummyRecipe>>()
    val todo: LiveData<List<DummyRecipe>> get() = _todo


private val _todos = MutableLiveData<Resource<List<DummyRecipe>>>(Resource.Loading())
     val todos: LiveData<Resource<List<DummyRecipe>>> get() = _todos

    fun singleRecipe(id :Int) = viewModelScope.launch(Dispatchers.Main){
        val response = repo.singleRecipe(id)
        _todos.value = response


    }
}