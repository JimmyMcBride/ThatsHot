package com.example.thatshot.adapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.thatshot.databinding.IngredientItemBinding
import com.example.thatshot.extensions.layoutInflater
import com.example.thatshot.repo.models.DummyIngredient

class IngredientListAdapter(
    private val ingredients: List<DummyIngredient>,
    private val editable: Boolean
) : RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = IngredientViewHolder.getInstance(parent)

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.loadIngredient(ingredients[position], editable)
    }

    override fun getItemCount() = ingredients.size

    class IngredientViewHolder(
        private val binding: IngredientItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadIngredient(ingredient: DummyIngredient, editable: Boolean) = with(binding) {
            tvIngredient.text = "${ingredient.amount} ${ingredient.unit} of ${ingredient.name}"
            if (editable) {
                btnEditRecipeName.isVisible = true
                btnEditRecipeName.setOnClickListener {
                    llEditIngredient.isVisible = true
                    llIngredientView.isVisible = false
                }
                btnSaveIngredient.setOnClickListener {
                    llEditIngredient.isVisible = false
                    llIngredientView.isVisible = true
                }
                btnCancel.setOnClickListener {
                    llEditIngredient.isVisible = false
                    llIngredientView.isVisible = true
                }
            }
        }

        companion object {
            fun getInstance(parent: ViewGroup) = IngredientItemBinding.inflate(
                parent.layoutInflater, parent, false
            ).run { IngredientViewHolder(this) }
        }
    }
}