package com.example.thatshot.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.thatshot.repo.models.Ingredient
import com.example.thatshot.repo.models.Recipe

val ViewGroup.layoutInflater: LayoutInflater get() = LayoutInflater.from(context)

val sampleRecipes by lazy {
    mutableListOf(
        Recipe(
            1,
            "Hot Dog",
            "Perfect for ball games",
            mutableListOf(
                Ingredient(1, "Hot dog", 1.0, "unit"),
                Ingredient(2, "Hot dog bun", 1.0, "unit"),
            )
        ),
        Recipe(
            2,
            "Hamburger",
            "Perfect for anytime",
            mutableListOf(
                Ingredient(3, "Burger patty", 1.0, "unit"),
                Ingredient(4, "Burger bun", 1.0, "unit"),
                Ingredient(5, "Lettuce", 1.0, "slice"),
            )
        )
    )
}