package com.example.thatshot.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.thatshot.R
import com.example.thatshot.adapter.RecipeListAdapter
import com.example.thatshot.databinding.FragmentHomeBinding
import com.example.thatshot.extensions.sampleRecipes
import com.example.thatshot.repo.ThatsHotRepoImplementation
import com.example.thatshot.repo.models.Recipe
import com.example.thatshot.util.StateResource
import com.example.thatshot.viewmodel.RecipeListViewModel
import com.example.thatshot.viewmodel.RecipeListViewmodelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val repo by lazy {
        ThatsHotRepoImplementation(requireContext())
    }
    private val viewModel by lazy {
        ViewModelProvider(this, RecipeListViewmodelFactory(repo))[RecipeListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        activity?.actionBar?.title = getString(R.string.app_name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() = with(binding) {
        btnAddRecipe.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.goToAddRecipeFragment())
        }
        btnAddSample.setOnClickListener {
            viewModel.addRecipe(sampleRecipes[0])
            viewModel.addRecipe(sampleRecipes[1])
        }
        viewModel.state.observe(viewLifecycleOwner) {
            when(it){
                is StateResource.Error -> {
                    Log.d("HOME_FRAGMENT", it.err.toString())
                    viewModel.setStandby()
                }
                is StateResource.Loading -> {
                    disableButtons()
                }
                is StateResource.Standby -> {
                    enableButtons()
                }
                is StateResource.Success -> {
                    rvRecipes.adapter = RecipeListAdapter(
                        it.data, ::recipeSelected
                    )
                    viewModel.setStandby()
                }
            }
        }
        viewModel.fetchRecipes()
    }

    private fun recipeSelected(recipe: Recipe) = with(findNavController()) {
        navigate(HomeFragmentDirections.goToViewRecipeFragment(recipe.id))
    }

    private fun enableButtons() = with(binding) {
        btnAddRecipe.isEnabled = true
    }

    private fun disableButtons() = with(binding) {
        btnAddRecipe.isEnabled = false
    }
}