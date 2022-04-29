package com.example.thatshot.viewmodels

import androidx.lifecycle.ViewModel
import com.stephan.lib_recipe.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {
}