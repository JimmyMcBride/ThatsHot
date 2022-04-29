package com.example.thatshot.view.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thatshot.view.adapter.IngredientListAdapter
import com.example.thatshot.databinding.FragmentEditRecipeBinding
import com.stephan.lib_recipe.domain.models.Ingredient
import com.stephan.lib_recipe.domain.models.Recipe
import com.stephan.lib_recipe.util.Resource
import com.example.thatshot.viewmodels.EditRecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditRecipeFragment : Fragment() {
    private var _binding: FragmentEditRecipeBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<EditRecipeFragmentArgs>()

    private var ingredients = listOf<Ingredient>()

//    private val repoImp by lazy {
//        LocalDataSourceImpl(requireContext())
//    }

    private val viewModel by viewModels<EditRecipeViewModel>()
//    {
//        ViewModelFactoryEditRecipe(repoImp)
//    }

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
                is com.stephan.lib_recipe.util.Resource.Error<*> -> {
                    Toast.makeText(context, ingredientViewState.message, Toast.LENGTH_SHORT).show()
                }
                is com.stephan.lib_recipe.util.Resource.Success<*> -> {
                    ingredients = ingredientViewState.data as List<Ingredient>
                    rvIngredients.adapter = IngredientListAdapter(ingredients, false)
                }
                is com.stephan.lib_recipe.util.Resource.Loading<*> -> {}
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
            viewModel.addRecipe(
                Recipe(
                id = args.recipe.id,
                recipeId = args.recipe.recipeId,
                recipe = itRecipeName.text.toString(),
                description = itRecipeDescription.text.toString()
            )
            )
            val action = EditRecipeFragmentDirections.goToHomeFragment()
            findNavController().navigate(action)
        }else if (!itRecipeName.text.isNullOrEmpty()){
            viewModel.addRecipe(Recipe(
                id = args.recipe.id,
                recipeId = args.recipe.recipeId,
                recipe = itRecipeName.text.toString(),
                description = args.recipe.description,
            ))
            val action = EditRecipeFragmentDirections.goToHomeFragment()
            findNavController().navigate(action)
        }else if (!itRecipeDescription.text.isNullOrEmpty()){
            viewModel.addRecipe(Recipe(
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