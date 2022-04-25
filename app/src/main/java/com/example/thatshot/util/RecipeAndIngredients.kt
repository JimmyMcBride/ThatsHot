package com.example.thatshot.util

import androidx.room.Embedded
import androidx.room.Relation
import com.example.thatshot.repo.models.DummyIngredient
import com.example.thatshot.repo.models.DummyRecipe

data class RecipeAndIngredients(
    @Embedded val recipe : DummyRecipe,
    @Relation(
        parentColumn = "recipe",
        entityColumn = "recipe"
    )
    val ingredient: DummyIngredient
)
