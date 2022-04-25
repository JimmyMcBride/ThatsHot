package com.example.thatshot.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thatshot.databinding.RecipeCardBinding
import com.example.thatshot.extensions.layoutInflater
import com.example.thatshot.repo.models.DummyRecipe

class RecipeListAdapter(
    private val recipes: List<DummyRecipe>,
    private val selectedRecipe: (DummyRecipe) -> Unit
) : RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = RecipeViewHolder.getInstance(parent).apply {
        itemView.setOnClickListener { selectedRecipe(recipes[adapterPosition]) }
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.loadRecipes(recipes[position])
    }

    override fun getItemCount() = recipes.size

    class RecipeViewHolder(
        private val binding: RecipeCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadRecipes(recipe: DummyRecipe) = with(binding) {
            tvRecipeName.text = recipe.recipe
            tvRecipeDescription.text = recipe.description
        }

        companion object {
            fun getInstance(parent: ViewGroup) = RecipeCardBinding.inflate(
                parent.layoutInflater, parent, false
            ).run { RecipeViewHolder(this) }
        }
    }
}