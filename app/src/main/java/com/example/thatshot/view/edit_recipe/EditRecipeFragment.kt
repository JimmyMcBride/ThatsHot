package com.example.thatshot.view.edit_recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thatshot.adapter.IngredientListAdapter
import com.example.thatshot.databinding.FragmentEditRecipeBinding

class EditRecipeFragment : Fragment() {
    private var _binding: FragmentEditRecipeBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<EditRecipeFragmentArgs>()
//    private val viewModel by viewModel<>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentEditRecipeBinding.inflate(
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
        rvIngredients.adapter = IngredientListAdapter(args.recipe.ingredients, true)
        tvRecipeName.text = args.recipe.name
        tvRecipeDescription.text = args.recipe.description
        btnGoBack.setOnClickListener {
            findNavController().navigateUp()
        }
        btnEditRecipeName.setOnClickListener {
            llEditRecipeName.isVisible = true
            llViewRecipeName.isVisible = false
        }
        btnEditRecipeDescription.setOnClickListener {
            llEditRecipeDescription.isVisible = true
            llViewRecipeDescription.isVisible = false
        }
        btnSaveRecipeName.setOnClickListener {
            llEditRecipeName.isVisible = false
            llViewRecipeName.isVisible = true
        }
        btnSaveRecipeDescription.setOnClickListener {
            llEditRecipeDescription.isVisible = false
            llViewRecipeDescription.isVisible = true
        }
        btnCancelRecipeName.setOnClickListener {
            llEditRecipeName.isVisible = false
            llViewRecipeName.isVisible = true
        }
        btnCancelRecipeDescription.setOnClickListener {
            llEditRecipeDescription.isVisible = false
            llViewRecipeDescription.isVisible = true
        }
    }
}