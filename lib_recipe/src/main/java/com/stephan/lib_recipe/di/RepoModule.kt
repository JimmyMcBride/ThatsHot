package com.stephan.lib_recipe.di

import com.stephan.lib_recipe.data.repository.Repository
import com.stephan.lib_recipe.domain.use_cases.UseCases
import com.stephan.lib_recipe.domain.use_cases.cross_use_cases.DeleteIngFromRecipeUseCase
import com.stephan.lib_recipe.domain.use_cases.cross_use_cases.GetRecipeAndIngredientUseCase
import com.stephan.lib_recipe.domain.use_cases.ingredient_use_cases.DeleteIngredientUseCase
import com.stephan.lib_recipe.domain.use_cases.ingredient_use_cases.GetAnyIngredientUseCase
import com.stephan.lib_recipe.domain.use_cases.ingredient_use_cases.GetIngredientsUseCase
import com.stephan.lib_recipe.domain.use_cases.ingredient_use_cases.InsertIngredientUseCase
import com.stephan.lib_recipe.domain.use_cases.recipe_use_cases.AddRecipeUseCase
import com.stephan.lib_recipe.domain.use_cases.recipe_use_cases.DeleteRecipeUseCase
import com.stephan.lib_recipe.domain.use_cases.recipe_use_cases.GetRecipeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun providesUseCases(repository: Repository) : UseCases {
        return UseCases(
            getRecipeUseCase = GetRecipeUseCase(repository),
            addRecipeUseCase = AddRecipeUseCase(repository),
            deleteRecipeUseCase = DeleteRecipeUseCase(repository),
            deleteIngredientUseCase = DeleteIngredientUseCase(repository),
            getIngredientsUseCase = GetIngredientsUseCase(repository),
            insertIngredientUseCase = InsertIngredientUseCase(repository),
            getRecipeAndIngredientUseCase = GetRecipeAndIngredientUseCase(repository),
            getAnyIngredientUseCase = GetAnyIngredientUseCase(repository),
            deleteIngFromRecipeUseCase = DeleteIngFromRecipeUseCase(repository)
        )
    }

}