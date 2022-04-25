package com.example.thatshot.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thatshot.views.adapter.IngredientListAdapter
import com.example.thatshot.databinding.FragmentViewRecipeBinding
import com.example.thatshot.model.repo.RepoImpl
import com.example.thatshot.util.Resource
import com.example.thatshot.viewmodels.HomeViewModel
import com.example.thatshot.viewmodels.ViewRecipeViewModel
import com.example.thatshot.viewmodels.factories.ViewModelFactoryHome
import com.example.thatshot.viewmodels.factories.ViewModelFactoryViewRecipe
import com.example.thatshot.views.adapter.RecipeListAdapter

class ViewRecipeFragment : Fragment() {
    private var _binding: FragmentViewRecipeBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ViewRecipeFragmentArgs>()
    private val repoImpl by lazy {
        RepoImpl(requireContext())
    }

    val viewModel by viewModels<ViewRecipeViewModel> {
        ViewModelFactoryViewRecipe(repoImpl)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentViewRecipeBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        initViews()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() = with(binding) {
        tvRecipeName.text = args.recipe.name
        tvRecipeDescription.text = args.recipe.description
        viewModel.getRecipeIngredients(args.recipe.id)
        viewModel.ingredients.observe(viewLifecycleOwner) { viewState ->
            when (viewState){
                is Resource.Error -> {
                    Toast.makeText(context, viewState.errorMsg, Toast.LENGTH_SHORT)
                }
                is Resource.Loading -> {
                    // Circular progress bar
                }
                is Resource.Success -> {
                    // Hide progress bar
                    binding.rvIngredients.adapter = IngredientListAdapter(viewState.data, false)
                }
            }
        }
        btnEditRecipe.setOnClickListener {
            findNavController().navigate(ViewRecipeFragmentDirections.goToEditRecipeFragment(args.recipe))
        }
        btnDeleteRecipe.setOnClickListener {
            viewModel.deleteRecipe(args.recipe)
            findNavController().navigateUp()
        }
    }
}