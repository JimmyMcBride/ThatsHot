package com.example.thatshot.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thatshot.R
import com.example.thatshot.adapter.RecipeListAdapter
import com.example.thatshot.databinding.FragmentHomeBinding
import com.example.thatshot.repo.RepoImpl
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.viewmodels.HomeViewModel
import com.example.thatshot.viewmodels.factories.ViewModelFactoryHome

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
//    private val viewModel by viewModel<>()

    val repoImpl by lazy {
        RepoImpl(requireContext())
    }

    val vm by viewModels<HomeViewModel> {
        ViewModelFactoryHome(repoImpl)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initViews()
        initListeners()
        activity?.actionBar?.title = getString(R.string.app_name)

    }

    override fun onResume() {
        super.onResume()
        vm.getRecipeList()
    }

    private fun initListeners() = with(binding) {
        btnAddRecipe.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.goToAddRecipeFragment())
        }
        rvRecipes.layoutManager = LinearLayoutManager(context)
        vm.recipes.observe(viewLifecycleOwner) { viewState ->
            rvRecipes.adapter = RecipeListAdapter(viewState, ::recipeSelected).apply {
                applyRecipeData(viewState)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun initViews() = with(binding) {
//        btnAddRecipe.setOnClickListener {
//            findNavController().navigate(HomeFragmentDirections.goToAddRecipeFragment())
//        }
//        rvRecipes.adapter = context?.data?.let {
//            RecipeListAdapter(
//                it, ::recipeSelected
//            )
//        }
//    }

    private fun recipeSelected(recipe: DummyRecipe) = with(findNavController()) {
        navigate(HomeFragmentDirections.goToViewRecipeFragment(recipe))
    }
}