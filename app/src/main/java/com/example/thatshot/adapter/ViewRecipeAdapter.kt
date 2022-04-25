package com.example.thatshot.adapter

import OnSwipeTouchListener
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.thatshot.databinding.FragmentEditRecipeBinding
import com.example.thatshot.databinding.RecipeCardBinding
import com.example.thatshot.extensions.layoutInflater
import com.example.thatshot.repo.models.DummyRecipe
import kotlin.reflect.KFunction1

class ViewRecipeAdapter(
    private val recipes: List<DummyRecipe>,
) : RecyclerView.Adapter<ViewRecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = RecipeViewHolder.getInstance(parent)

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
            fun getInstance(parent: ViewGroup) = RecipeCardBinding.inflate (
                parent.layoutInflater, parent, false
            ).run { RecipeViewHolder(this) }
        }
     }
    }