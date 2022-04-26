package com.example.thatshot.di

import com.example.thatshot.data.repository.Repository
import com.example.thatshot.domain.use_cases.UseCases
import com.example.thatshot.domain.use_cases.get_recipes.GetRecipesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            getRecipesUseCase = GetRecipesUseCase(repository)
        )
    }
}