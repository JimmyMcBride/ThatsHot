package com.stephan.lib_recipe.domain.use_cases

import com.stephan.lib_recipe.domain.use_cases.cross_use_cases.DeleteIngFromRecipeUseCase
import com.stephan.lib_recipe.domain.use_cases.cross_use_cases.GetRecipeAndIngredientUseCase
import com.stephan.lib_recipe.domain.use_cases.ingredient_use_cases.DeleteIngredientUseCase
import com.stephan.lib_recipe.domain.use_cases.ingredient_use_cases.GetAnyIngredientUseCase
import com.stephan.lib_recipe.domain.use_cases.ingredient_use_cases.GetIngredientsUseCase
import com.stephan.lib_recipe.domain.use_cases.ingredient_use_cases.InsertIngredientUseCase
import com.stephan.lib_recipe.domain.use_cases.recipe_use_cases.AddRecipeUseCase
import com.stephan.lib_recipe.domain.use_cases.recipe_use_cases.DeleteRecipeUseCase
import com.stephan.lib_recipe.domain.use_cases.recipe_use_cases.GetRecipeUseCase
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