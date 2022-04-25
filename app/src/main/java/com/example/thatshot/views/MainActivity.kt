package com.example.thatshot.views

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.thatshot.R
import com.example.thatshot.databinding.ActivityMainBinding
import com.example.thatshot.model.models.Ingredient
import com.example.thatshot.model.models.Recipe


val Context.data by lazy {
    mutableListOf(
        Recipe(
            1,
            "Hot Dog",
            "Perfect for ball games",
//            mutableListOf(
//                Ingredient(1.0,"Hot dog", "unit"),
//                Ingredient("Hot dog bun", 1.0, "unit"),
//            )
        ),
        Recipe(
            2,
            "Hamburger",
            "Perfect for anytime",
//            mutableListOf(
//                Ingredient(3, "Burger patty", 1.0, "unit"),
//                Ingredient(4, "Burger bun", 1.0, "unit"),
//                Ingredient(5, "Lettuce", 1.0, "slice"),
//            )
        )
    )
}

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)

        supportActionBar?.elevation = 0F
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}