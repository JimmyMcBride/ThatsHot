package com.stephan.lib_recipe.util

import androidx.room.Embedded
import androidx.room.Relation
import com.stephan.lib_recipe.domain.models.Ingredient
import com.stephan.lib_recipe.domain.models.Recipe

data class RecipeAndIngredients(
    @Embedded val recipe : Recipe,
    @Relation(
        parentColumn = "recipe",
        entityColumn = "recipe"
    )
    val ingredient: Ingredient
)
