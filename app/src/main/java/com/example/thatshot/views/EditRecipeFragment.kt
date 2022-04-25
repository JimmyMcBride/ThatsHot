package com.example.thatshot.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thatshot.views.adapter.IngredientListAdapter
import com.example.thatshot.databinding.FragmentEditRecipeBinding
import com.example.thatshot.model.repo.RepoImpl
import com.example.thatshot.viewmodels.AddRecipeViewModel
import com.example.thatshot.viewmodels.EditRecipeViewModel
import com.example.thatshot.viewmodels.factories.ViewModelFactoryAddRecipe
import com.example.thatshot.viewmodels.factories.ViewModelFactoryEditRecipe

class EditRecipeFragment : Fragment() {
    private var _binding: FragmentEditRecipeBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<EditRecipeFragmentArgs>()
    private val recipeId by lazy {
        args.recipe.id
    }
    private val repo by lazy {
        RepoImpl(requireContext())
    }
    val viewModel by viewModels<EditRecipeViewModel> {
        ViewModelFactoryEditRecipe(repo)
    }
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
//        rvIngredients.adapter = IngredientListAdapter(args.recipe.ingredients, true)
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
            val newName = binding.itRecipeName.text.toString()
            viewModel.updateRecipeName(recipeId, newName)
            tvRecipeName.text = newName
            llEditRecipeName.isVisible = false
            llViewRecipeName.isVisible = true
        }
        btnSaveRecipeDescription.setOnClickListener {
            val newDescription = binding.itRecipeDescription.text.toString()
            viewModel.updateRecipeDescription(recipeId, newDescription)
            tvRecipeDescription.text = newDescription
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