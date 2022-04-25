package com.example.thatshot.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thatshot.R
import com.example.thatshot.views.adapter.RecipeListAdapter
import com.example.thatshot.databinding.FragmentHomeBinding
import com.example.thatshot.model.models.Recipe
import com.example.thatshot.model.repo.RepoImpl
import com.example.thatshot.util.Resource
import com.example.thatshot.viewmodels.HomeViewModel
import com.example.thatshot.viewmodels.factories.ViewModelFactoryHome

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val repoImpl by lazy {
        RepoImpl(requireContext())
    }
    val viewModel by viewModels<HomeViewModel> {
        ViewModelFactoryHome(repoImpl)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initData()
        initViews()
        initObserver()
        activity?.actionBar?.title = getString(R.string.app_name)

    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllRecipes()
    }
    override fun onResume() {
        super.onResume()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() = with(binding) {
        btnAddRecipe.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.goToAddRecipeFragment())
        }
//        rvRecipes.adapter = context?.data?.let {
//            RecipeListAdapter(
//                it, ::recipeSelected
//            )
//        }

    }

    private fun initObserver() {
        viewModel.recipes.observe(viewLifecycleOwner) { viewState ->
            when (viewState){
                is Resource.Error -> {
                    Toast.makeText(context, viewState.errorMsg, Toast.LENGTH_SHORT)
                }
                is Resource.Loading -> {
                    // Circular progress bar
                }
                is Resource.Success -> {
                    // Hide progress bar
                    binding.rvRecipes.adapter = RecipeListAdapter(viewState.data, ::recipeSelected)
                }
            }
        }
    }

    private fun recipeSelected(recipe: Recipe) = with(findNavController()) {
        navigate(HomeFragmentDirections.goToViewRecipeFragment(recipe))
    }
}