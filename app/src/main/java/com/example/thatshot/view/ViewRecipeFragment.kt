package com.example.thatshot.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thatshot.adapter.IngredientListAdapter
import com.example.thatshot.databinding.FragmentViewRecipeBinding
import com.example.thatshot.repo.ThatsHotRepoImplementation
import com.example.thatshot.util.StateResource
import com.example.thatshot.viewmodel.RecipeListViewModel
import com.example.thatshot.viewmodel.RecipeListViewmodelFactory

class ViewRecipeFragment : Fragment() {

    private var _binding: FragmentViewRecipeBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ViewRecipeFragmentArgs>()

    private val repo by lazy {
        ThatsHotRepoImplementation(requireContext())
    }

    private val viewModel by lazy {
        ViewModelProvider(this, RecipeListViewmodelFactory(repo))[RecipeListViewModel::class.java]
    }

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
        viewModel.state.observe(viewLifecycleOwner) {
            when(it) {
                is StateResource.Error -> {
                    Log.d("VIEW_FRAGMENT", it.err.toString())
                    viewModel.setStandby()
                }

                is StateResource.Loading -> {
                    disableButtons()
                }

                is StateResource.Standby -> {
                    enableButtons()
                }

                is StateResource.Success -> {
                    tvRecipeName.text = it.data[0].name
                    tvRecipeDescription.text = it.data[0].description
                    rvIngredients.adapter = IngredientListAdapter(it.data[0].ingredients, false)
                    viewModel.setStandby()
                }
            }
        }

        btnEditRecipe.setOnClickListener {
            findNavController().navigate(ViewRecipeFragmentDirections.goToEditRecipeFragment(args.recipeID))
        }

        btnDeleteRecipe.setOnClickListener {
            viewModel.deleteByID(args.recipeID)

            findNavController().navigateUp()
        }

        viewModel.fetchByID(args.recipeID)
    }


    private fun enableButtons() = with(binding) {
        btnDeleteRecipe.isEnabled = true
        btnEditRecipe.isEnabled = true
    }

    private fun disableButtons() = with(binding) {
        btnDeleteRecipe.isEnabled = false
        btnEditRecipe.isEnabled = false
    }
}