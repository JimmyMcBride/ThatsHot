package com.example.thatshot.view.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thatshot.R
import com.example.thatshot.view.adapter.RecipeListAdapter
import com.example.thatshot.databinding.FragmentHomeBinding
import com.stephan.lib_recipe.domain.models.Recipe
import com.stephan.lib_recipe.util.Resource
import com.example.thatshot.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

//    private val repoImp by lazy {
//        LocalDataSourceImpl(requireContext())
//    }

    private val viewModel by viewModels<HomeViewModel>()
//    {
//        ViewModelFactoryHome(repoImp)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(
        inflater, container, false
    ).also {
        _binding = it
        viewModel.getAllRecipes()
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        activity?.actionBar?.title = getString(R.string.app_name)

    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllRecipes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() = with(binding) {
        var currentList = listOf<Recipe>()
        btnAddRecipe.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddRecipeFragment(currentList.count() + 1))
        }
        viewModel.allRecipes.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is com.stephan.lib_recipe.util.Resource.Error<*> -> {
                    Toast.makeText(context, viewState.message, Toast.LENGTH_SHORT).show()
                }
                is com.stephan.lib_recipe.util.Resource.Success<*> -> {
                    currentList = viewState.data as List<Recipe>
                    rvRecipes.adapter = RecipeListAdapter(viewState.data,::recipeSelected)
                }
                is com.stephan.lib_recipe.util.Resource.Loading<*> -> {}
                else -> {}
            }
        }
    }

    private fun recipeSelected(recipe: Recipe) = with(findNavController()) {
        navigate(HomeFragmentDirections.actionHomeFragmentToViewRecipeFragment(recipe))
    }
}