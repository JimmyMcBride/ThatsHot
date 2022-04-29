package com.example.thatshot.util

import androidx.room.Embedded
import androidx.room.Relation
import com.example.thatshot.domain.models.Ingredient
import com.example.thatshot.domain.models.Recipe

data class RecipeAndIngredients(
    @Embedded val recipe : Recipe,
    @Relation(
        parentColumn = "recipe",
        entityColumn = "recipe"
    )
    val ingredient: Ingredient
)
