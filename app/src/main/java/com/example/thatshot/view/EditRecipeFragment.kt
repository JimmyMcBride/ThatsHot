package com.example.thatshot.view

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thatshot.adapter.IngredientListAdapter
import com.example.thatshot.databinding.FragmentEditRecipeBinding
import com.example.thatshot.repo.RepoImpl
import com.example.thatshot.repo.models.DummyIngredient
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.util.Resource
import com.example.thatshot.viewholder.AddRecipeViewModel
import com.example.thatshot.viewholder.EditRecipeViewModel
import com.example.thatshot.viewholder.factory.ViewModelFactoryAddRecipe
import com.example.thatshot.viewholder.factory.ViewModelFactoryEditRecipe

class EditRecipeFragment : Fragment() {
    private var _binding: FragmentEditRecipeBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<EditRecipeFragmentArgs>()

    private var ingredients = listOf<DummyIngredient>()

    private val repoImp by lazy {
        RepoImpl(requireContext())
    }

    private val viewModel by viewModels<EditRecipeViewModel>() {
        ViewModelFactoryEditRecipe(repoImp)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentEditRecipeBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getIngredients(args.recipe.recipeId)
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun initViews() = with(binding) {
        viewModel.allIngredients.observe(viewLifecycleOwner) { ingredientViewState ->
            when (ingredientViewState) {
                is Resource.Error -> {
                    Toast.makeText(context, ingredientViewState.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    ingredients = ingredientViewState.data
                    rvIngredients.adapter = IngredientListAdapter(ingredients, false)
                }
                is Resource.Loading -> {}
                else -> {}
            }
        }
        tvRecipeName.text = args.recipe.recipe
        tvRecipeDescription.text = args.recipe.description
        btnGoBack.setOnClickListener {
            validateInputs()
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
            if (itRecipeName.text!!.isNotEmpty()){
            tvRecipeName.text = itRecipeName.text
            }
            llEditRecipeName.isVisible = false
            llViewRecipeName.isVisible = true
        }
        btnSaveRecipeDescription.setOnClickListener {
            if (itRecipeDescription.text!!.isNotEmpty()){
                tvRecipeDescription.text = itRecipeDescription.text
            }
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
    fun validateInputs() = with(binding){
        if (itRecipeName.text.isNullOrEmpty() && itRecipeDescription.text.isNullOrEmpty()){
            findNavController().navigateUp()
        }else if(!itRecipeName.text.isNullOrEmpty() && !itRecipeDescription.text.isNullOrEmpty()){
            viewModel.addRecipe(DummyRecipe(
                id = args.recipe.id,
                recipeId = args.recipe.recipeId,
                recipe = itRecipeName.text.toString(),
                description = itRecipeDescription.text.toString()
            ))
            val action = EditRecipeFragmentDirections.goToHomeFragment()
            findNavController().navigate(action)
        }else if (!itRecipeName.text.isNullOrEmpty()){
            viewModel.addRecipe(DummyRecipe(
                id = args.recipe.id,
                recipeId = args.recipe.recipeId,
                recipe = itRecipeName.text.toString(),
                description = args.recipe.description,
            ))
            val action = EditRecipeFragmentDirections.goToHomeFragment()
            findNavController().navigate(action)
        }else if (!itRecipeDescription.text.isNullOrEmpty()){
            viewModel.addRecipe(DummyRecipe(
                id = args.recipe.id,
                recipeId = args.recipe.recipeId,
                recipe = args.recipe.recipe,
                description = itRecipeDescription.text.toString(),
            ))
        val action = EditRecipeFragmentDirections.goToHomeFragment()
        findNavController().navigate(action)
        }
        else{
            findNavController().navigateUp()
        }
    }
}