package com.example.thatshot.repo.room

import androidx.room.*
import com.example.thatshot.repo.models.DummyIngredient
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.util.RecipeAndIngredients

@Dao
interface DummyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: DummyRecipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredient(ingredient: DummyIngredient)

    @Delete
    suspend fun deleteRecipe(recipe: DummyRecipe)

    @Delete
    suspend fun deleteIngredient(ingredient: DummyIngredient)

    @Transaction
    @Query("SELECT * FROM dummy_recipe WHERE recipe = :recipe")
    suspend fun getRecipeAndIngredients(recipe: String): List<RecipeAndIngredients>

    @Query("SELECT * FROM dummy_recipe")
    suspend fun getRecipes(): List<DummyRecipe>

    @Query("SELECT * FROM dummy_ingredients WHERE recipe = :recipe")
    suspend fun getIngredients(recipe: Int): List<DummyIngredient>

    @Query("SELECT * FROM dummy_ingredients")
    suspend fun getAnyIngredients(): List<DummyIngredient>

    @Query("DELETE FROM dummy_ingredients WHERE recipe = :recipeID")
    suspend fun deleteIngredientsInRecipe(recipeID: Int) : Int
}