package com.example.thatshot.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.thatshot.R
import com.example.thatshot.adapter.RecipeListAdapter
import com.example.thatshot.databinding.FragmentHomeBinding
import com.example.thatshot.domain.model.Recipe
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment @Inject constructor(
//    private var viewModelFactory: ViewModelProvider.Factory
) : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
//    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.title = getString(R.string.app_name)
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() = with(binding) {
        lifecycleScope.launch {
//            viewModel.getRecipes()
        }
        btnAddRecipe.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.goToAddRecipeFragment())
        }
//        rvRecipes.adapter = RecipeListAdapter(
//            viewModel.recipes.value, ::recipeSelected
//        )
    }

    private fun recipeSelected(recipe: Recipe) = with(findNavController()) {
        navigate(HomeFragmentDirections.goToViewRecipeFragment(recipe))
    }
}