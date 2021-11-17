package com.example.thatshot.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.thatshot.R
import com.example.thatshot.models.DummyIngredient
import com.example.thatshot.models.DummyRecipe

val Context.data by lazy {
    mutableListOf<DummyRecipe>(
        DummyRecipe(
            1,
            "Hot Dog",
            "Perfect for ball games",
            mutableListOf<DummyIngredient>(
                DummyIngredient(1, "Hot dog", 1.0, "unit"),
                DummyIngredient(2, "Hot dog bun", 1.0, "unit"),
            )
        ),
        DummyRecipe(
            2,
            "Hamburger",
            "Perfect for anytime",
            mutableListOf<DummyIngredient>(
                DummyIngredient(3, "Burger patty", 1.0, "unit"),
                DummyIngredient(4, "Burger bun", 1.0, "unit"),
                DummyIngredient(5, "Lettuce", 1.0, "slice"),
            )
        )
    )
}

class MainActivity : AppCompatActivity(R.layout.activity_main)