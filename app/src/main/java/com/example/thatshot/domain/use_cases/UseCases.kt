package com.example.thatshot.domain.use_cases

import com.example.thatshot.domain.use_cases.cross_use_cases.DeleteIngFromRecipeUseCase
import com.example.thatshot.domain.use_cases.cross_use_cases.GetRecipeAndIngredientUseCase
import com.example.thatshot.domain.use_cases.ingredient_use_cases.DeleteIngredientUseCase
import com.example.thatshot.domain.use_cases.ingredient_use_cases.GetAnyIngredientUseCase
import com.example.thatshot.domain.use_cases.ingredient_use_cases.GetIngredientsUseCase
import com.example.thatshot.domain.use_cases.ingredient_use_cases.InsertIngredientUseCase
import com.example.thatshot.domain.use_cases.recipe_use_cases.AddRecipeUseCase
import com.example.thatshot.domain.use_cases.recipe_use_cases.DeleteRecipeUseCase
import com.example.thatshot.domain.use_cases.recipe_use_cases.GetRecipeUseCase
import javax.inject.Inject

data class UseCases @Inject constructor(
    val getRecipeUseCase: GetRecipeUseCase,
    val addRecipeUseCase: AddRecipeUseCase,
    val deleteRecipeUseCase: DeleteRecipeUseCase,
    val deleteIngredientUseCase: DeleteIngredientUseCase,
    val getIngredientsUseCase: GetIngredientsUseCase,
    val insertIngredientUseCase: InsertIngredientUseCase,
    val getRecipeAndIngredientUseCase: GetRecipeAndIngredientUseCase,
    val getAnyIngredientUseCase: GetAnyIngredientUseCase,
    val deleteIngFromRecipeUseCase: DeleteIngFromRecipeUseCase
)