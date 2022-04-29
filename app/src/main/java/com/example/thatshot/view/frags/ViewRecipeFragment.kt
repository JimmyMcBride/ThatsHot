package com.example.thatshot.view.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thatshot.adapter.IngredientListAdapter
import com.example.thatshot.databinding.FragmentViewRecipeBinding
import com.example.thatshot.domain.models.Ingredient
import com.example.thatshot.util.Resource
import com.example.thatshot.viewmodels.ViewRecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewRecipeFragment : Fragment() {
    private var _binding: FragmentViewRecipeBinding? = null
    private val binding get() = _binding!!

//    val repoImp by lazy {
//        LocalDataSourceImpl(requireContext())
//    }

    private val viewModel by viewModels<ViewRecipeViewModel>()
//    {
//        ViewModelFactoryViewRecipe(repoImp)
//    }

    private var ingredients = listOf<Ingredient>()

    private val args by navArgs<ViewRecipeFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentViewRecipeBinding.inflate(
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
        tvRecipeName.text = args.recipe.recipe
        tvRecipeDescription.text = args.recipe.description
        btnEditRecipe.setOnClickListener {
            findNavController().navigate(ViewRecipeFragmentDirections.actionViewRecipeFragmentToEditRecipeFragment(args.recipe))
        }
        btnDeleteRecipe.setOnClickListener {
            viewModel.deleteRecipe(args.recipe)
            viewModel.deleteIngredients(args.recipe.id)
            findNavController().navigateUp()
        }

        viewModel.allIngredients.observe(viewLifecycleOwner) { ingredientViewState ->
            when (ingredientViewState) {
                is Resource.Error<*> -> {
                    Toast.makeText(context, ingredientViewState.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success<*> -> {
                    ingredients = ingredientViewState.data as List<Ingredient>
                    rvIngredients.adapter = IngredientListAdapter(ingredients, false)
                }
                is Resource.Loading<*> -> {}
                else -> {}
            }
        }

    }
}