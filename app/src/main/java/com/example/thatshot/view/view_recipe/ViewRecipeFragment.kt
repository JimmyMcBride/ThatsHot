package com.example.thatshot.view.view_recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thatshot.adapter.IngredientListAdapter
import com.example.thatshot.databinding.FragmentViewRecipeBinding

class ViewRecipeFragment : Fragment() {
    private var _binding: FragmentViewRecipeBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ViewRecipeFragmentArgs>()
//    private val viewModel by viewModel<>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentViewRecipeBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() = with(binding) {
        tvRecipeName.text = args.recipe.name
        tvRecipeDescription.text = args.recipe.description
//        rvIngredients.adapter = IngredientListAdapter(args.recipe.ingredients, false)
        btnEditRecipe.setOnClickListener {
            findNavController().navigate(ViewRecipeFragmentDirections.goToEditRecipeFragment(args.recipe))
        }

    }
}