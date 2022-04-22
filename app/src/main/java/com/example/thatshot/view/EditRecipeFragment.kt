package com.example.thatshot.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thatshot.adapter.IngredientListAdapter
import com.example.thatshot.databinding.FragmentEditRecipeBinding
import com.example.thatshot.repo.ThatsHotRepoImplementation
import com.example.thatshot.repo.models.Recipe
import com.example.thatshot.util.StateResource
import com.example.thatshot.viewmodel.RecipeListViewModel
import com.example.thatshot.viewmodel.RecipeListViewmodelFactory

class EditRecipeFragment : Fragment() {

    private var _binding: FragmentEditRecipeBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<EditRecipeFragmentArgs>()

    private val repo by lazy {
        ThatsHotRepoImplementation(requireContext())
    }

    private val viewModel by lazy {
        ViewModelProvider(this, RecipeListViewmodelFactory(repo))[RecipeListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentEditRecipeBinding.inflate(
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

    lateinit var adapter: IngredientListAdapter

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
                    adapter = IngredientListAdapter(it.data[0].ingredients, true)
                    rvIngredients.adapter = adapter
                    viewModel.setStandby()
                }
            }
        }

        viewModel.fetchByID(args.recipeID)

        btnSaveRecipe.setOnClickListener {
            val newRecipe = Recipe (
                id = args.recipeID,
                name = tvRecipeName.text.toString(),
                description =  tvRecipeDescription.text.toString(),
                ingredients = adapter.ingredients)
            viewModel.update(newRecipe)

            findNavController().navigateUp()
        }

        btnGoBack.setOnClickListener {
            findNavController().navigateUp()
        }

        btnEditRecipeName.setOnClickListener {
            llEditRecipeName.isVisible = true
            llViewRecipeName.isVisible = false
        }

        btnEditRecipeDescription.setOnClickListener {
            llEditRecipeDescription.isVisible = true
            llViewRecipeDescription.isVisible = false
        }

        btnSaveRecipeName.setOnClickListener {
            llEditRecipeName.isVisible = false
            llViewRecipeName.isVisible = true
            tvRecipeName.text = itRecipeName.text
        }

        btnSaveRecipeDescription.setOnClickListener {
            llEditRecipeDescription.isVisible = false
            llViewRecipeDescription.isVisible = true
            tvRecipeDescription.text = itRecipeDescription.text
        }

        btnCancelRecipeName.setOnClickListener {
            llEditRecipeName.isVisible = false
            llViewRecipeName.isVisible = true
        }

        btnCancelRecipeDescription.setOnClickListener {
            llEditRecipeDescription.isVisible = false
            llViewRecipeDescription.isVisible = true
        }
    }

    private fun enableButtons() = with(binding) {
        btnSaveRecipe.isEnabled = true
        btnGoBack.isEnabled = true
        btnEditRecipeDescription.isEnabled = true
        btnEditRecipeName.isEnabled = true
    }

    private fun disableButtons() = with(binding) {
        btnSaveRecipe.isEnabled = false
        btnGoBack.isEnabled = false
        btnEditRecipeDescription.isEnabled = false
        btnEditRecipeName.isEnabled = false
    }
}