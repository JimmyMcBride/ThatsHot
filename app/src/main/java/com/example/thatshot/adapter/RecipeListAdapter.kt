package com.example.thatshot.adapter

import OnSwipeTouchListener
import android.annotation.SuppressLint
import android.service.autofill.OnClickAction
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.thatshot.databinding.RecipeCardBinding
import com.example.thatshot.extensions.layoutInflater
import com.example.thatshot.repo.models.DummyRecipe
import kotlin.reflect.KFunction1

class RecipeListAdapter(
    private val recipes: List<DummyRecipe>,
    val selectedRecipe:  (DummyRecipe) -> Unit,
    val deleteRecipe: (DummyRecipe) -> Unit
) : RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {
    var swipOrClick = false

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = RecipeViewHolder.getInstance(parent).apply {



        itemView.setOnTouchListener(object : OnSwipeTouchListener(itemView.context)

        {


            @SuppressLint("ClickableViewAccessibility")
            override fun onSwipeLeft() {
                swipOrClick = true
                super.onSwipeLeft()
                deleteRecipe(recipes[adapterPosition])
                Log.d("ddds","delete son of bitch")

            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                selectedRecipe(recipes[adapterPosition])


            }
            override fun onSwipeUp() {
                super.onSwipeUp()

            }
            override fun onSwipeDown() {
                super.onSwipeDown()

            }

        }
        )

        }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.loadRecipes(recipes[position])
    }

    override fun getItemCount() = recipes.size

    class RecipeViewHolder(
        private val binding: RecipeCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadRecipes(recipe: DummyRecipe) = with(binding) {
            tvRecipeName.text = recipe.name
            tvRecipeDescription.text = recipe.description
        }

        companion object {
            fun getInstance(parent: ViewGroup) = RecipeCardBinding.inflate(
                parent.layoutInflater, parent, false
            ).run { RecipeViewHolder(this) }
        }
    }

}
