package com.fireninja.lib_recipes.domain.use_cases

import com.fireninja.lib_recipes.domain.use_cases.get_recipes.GetRecipesUseCase
import javax.inject.Inject

data class UseCases @Inject constructor(
    val getRecipesUseCase: GetRecipesUseCase,
)
