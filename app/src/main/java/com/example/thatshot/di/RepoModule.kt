package com.example.thatshot.di

import com.example.thatshot.data.repository.Repository
import com.example.thatshot.domain.use_cases.UseCases
import com.example.thatshot.domain.use_cases.cross_use_cases.DeleteIngFromRecipeUseCase
import com.example.thatshot.domain.use_cases.cross_use_cases.GetRecipeAndIngredientUseCase
import com.example.thatshot.domain.use_cases.ingredient_use_cases.DeleteIngredientUseCase
import com.example.thatshot.domain.use_cases.ingredient_use_cases.GetAnyIngredientUseCase
import com.example.thatshot.domain.use_cases.ingredient_use_cases.GetIngredientsUseCase
import com.example.thatshot.domain.use_cases.ingredient_use_cases.InsertIngredientUseCase
import com.example.thatshot.domain.use_cases.recipe_use_cases.AddRecipeUseCase
import com.example.thatshot.domain.use_cases.recipe_use_cases.DeleteRecipeUseCase
import com.example.thatshot.domain.use_cases.recipe_use_cases.GetRecipeUseCase
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