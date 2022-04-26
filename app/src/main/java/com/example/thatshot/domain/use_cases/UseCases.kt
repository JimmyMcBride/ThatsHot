package com.example.thatshot.domain.use_cases

import com.example.thatshot.domain.use_cases.get_recipes.GetRecipesUseCase
import javax.inject.Inject

data class UseCases @Inject constructor(
    val getRecipesUseCase: GetRecipesUseCase,
)
