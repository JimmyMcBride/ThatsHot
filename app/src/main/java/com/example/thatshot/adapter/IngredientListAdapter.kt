package com.example.thatshot.adapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.thatshot.databinding.IngredientItemBinding
import com.example.thatshot.extensions.layoutInflater
import com.example.thatshot.repo.models.Ingredient

class IngredientListAdapter(
    val ingredients: List<Ingredient>,
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

        fun loadIngredient(ingredient: Ingredient, editable: Boolean) = with(binding) {
            tvIngredient.text = "${ingredient.amount} ${ingredient.unit} of ${ingredient.name}"
            var amount: String = ingredient.amount.toString()
            var unit: String = ingredient.unit
            var name: String = ingredient.name
            if (editable) {
                btnEditRecipeName.isVisible = true
                btnEditRecipeName.setOnClickListener {
                    itIngredientName.setText(name)
                    itIngredientUnit.setText(unit)
                    itIngredientAmount.setText(amount)
                    llEditIngredient.isVisible = true
                    llIngredientView.isVisible = false
                }
                btnSaveIngredient.setOnClickListener {
                    amount = itIngredientAmount.text.toString()
                    unit = itIngredientUnit.text.toString()
                    name = itIngredientName.text.toString()
                    tvIngredient.text = "${amount} ${unit} of ${name}"
                    llEditIngredient.isVisible = false
                    llIngredientView.isVisible = true
                }
                btnCancel.setOnClickListener {
                    amount = itIngredientAmount.text.toString()
                    unit = itIngredientUnit.text.toString()
                    name = itIngredientName.text.toString()
                    tvIngredient.text = "${amount} ${unit} of ${name}"
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