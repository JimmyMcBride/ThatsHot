package com.example.thatshot.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.thatshot.R
import com.example.thatshot.databinding.ActivityMainBinding
import com.example.thatshot.repo.models.DummyIngredient
import com.example.thatshot.repo.models.DummyRecipe


val Context.data by lazy {
    mutableListOf(
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

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        supportActionBar?.let {
                titleColor = getColor(R.color.gray_800)

        }

    }
}