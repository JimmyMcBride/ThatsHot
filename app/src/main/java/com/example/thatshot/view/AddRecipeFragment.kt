package com.example.thatshot.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.thatshot.adapter.IngredientListAdapter
import com.example.thatshot.databinding.FragmentAddRecipeBinding
import com.example.thatshot.models.DummyIngredient

class AddRecipeFragment : Fragment() {
    private var _binding: FragmentAddRecipeBinding? = null
    private val binding get() = _binding!!
//    private val viewModel by activityViewModels<>()

    private var ingredients = mutableListOf<DummyIngredient>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentAddRecipeBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() = with(binding) {
        rvIngredients.adapter = IngredientListAdapter(ingredients, false)
        btnQuitRecipe.setOnClickListener {
            findNavController().navigateUp()
        }
        btnSaveRecipe.setOnClickListener {
            findNavController().navigateUp()
        }
        btnAddIngredient.setOnClickListener {
            ingredients.add(
                DummyIngredient(
                    1,
                    itIngredientName.text.toString(),
                    itIngredientAmount.text.toString().toDouble(),
                    itIngredientUnit.text.toString()
                )
            )
            itIngredientName.text?.clear()
            itIngredientAmount.text?.clear()
            itIngredientUnit.text?.clear()
            rvIngredients.adapter = IngredientListAdapter(ingredients, false)
        }


    }
}