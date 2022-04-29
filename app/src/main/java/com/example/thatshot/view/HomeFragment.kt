package com.example.thatshot.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thatshot.R
import com.example.thatshot.adapter.RecipeListAdapter
import com.example.thatshot.databinding.FragmentHomeBinding
import com.example.lib_recipes.repo.RepoImpl
import com.example.lib_recipes.repo.models.DummyRecipe
import com.example.lib_recipes.util.Resource
import com.example.thatshot.viewholder.AddRecipeViewModel
import com.example.thatshot.viewholder.HomeViewModel
import com.example.thatshot.viewholder.factory.ViewModelFactoryHome

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val repoImp by lazy {
        com.example.lib_recipes.repo.RepoImpl(requireContext())
    }

    private val viewModel by viewModels<HomeViewModel>() {
        ViewModelFactoryHome(repoImp)
    }

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
        var currentList = listOf<DummyRecipe>()
        btnAddRecipe.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.goToAddRecipeFragment(currentList.count() + 1))
        }
        viewModel.allRecipes.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is Resource.Error -> {
                    Toast.makeText(context, viewState.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    currentList = viewState.data
                    rvRecipes.adapter = RecipeListAdapter(viewState.data,::recipeSelected)
                }
                is Resource.Loading -> {}
                else -> {}
            }
        }
    }

    private fun recipeSelected(recipe: DummyRecipe) = with(findNavController()) {
        navigate(HomeFragmentDirections.goToViewRecipeFragment(recipe))
    }
}