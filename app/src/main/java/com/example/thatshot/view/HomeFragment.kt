package com.example.thatshot.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.thatshot.R
import com.example.thatshot.adapter.RecipeListAdapter
import com.example.thatshot.databinding.FragmentHomeBinding
import com.example.thatshot.repo.models.DummyRecipe

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


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
        rvRecipes.adapter = context?.data?.let {
            RecipeListAdapter(
                listOf(it), ::recipeSelected
            )
        }
    }

    private fun recipeSelected(recipe: DummyRecipe) = with(findNavController()) {
        navigate(HomeFragmentDirections.goToViewRecipeFragment(recipe))
    }
}