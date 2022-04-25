package com.example.thatshot.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thatshot.adapter.IngredientListAdapter
import com.example.thatshot.databinding.FragmentEditRecipeBinding
import com.example.thatshot.repo.RepoImpl
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.viewmodels.AddRecipeViewModel
import com.example.thatshot.viewmodels.EditRecipeViewModel
import com.example.thatshot.viewmodels.factories.ViewModelFactoryAddRecipe
import com.example.thatshot.viewmodels.factories.ViewModelFactoryEditRecipe

class EditRecipeFragment : Fragment() {

    private var _binding: FragmentEditRecipeBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<EditRecipeFragmentArgs>()
//    private val viewModel by viewModel<>()

    val repoImpl by lazy {
        RepoImpl(requireContext())
    }

    val vm by viewModels<EditRecipeViewModel> {
        ViewModelFactoryEditRecipe(repoImpl)
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

    private fun initViews() = with(binding) {
//        rvIngredients.adapter = IngredientListAdapter(args.recipe.ingredients, true)
        tvRecipeName.text = args.recipe.name
        tvRecipeDescription.text = args.recipe.description
        btnGoBack.setOnClickListener {
            val recipe = DummyRecipe(
                id = args.recipe.id,
                name = args.recipe.name,
                description = args.recipe.description
            )
            vm.editRecipeEntry(recipe)
            findNavController().navigateUp()
        }
        btnSaveRecipe.setOnClickListener {
            val action = EditRecipeFragmentDirections.goToHomeFragment()
            findNavController().navigate(action)
        }
        btnEditRecipeName.setOnClickListener {
            llEditRecipeName.isVisible = true
            llViewRecipeName.isVisible = false
            btnSaveRecipeName.setOnClickListener {
                val recipe = DummyRecipe(
                    id = args.recipe.id,
                    name = itRecipeName.text.toString(),
                    description = args.recipe.description
                )
                vm.editRecipeEntry(recipe)
                tvRecipeName.text = recipe.name
                llEditRecipeName.isVisible = false
                llViewRecipeName.isVisible = true
            }
        }
        btnEditRecipeDescription.setOnClickListener {
            llEditRecipeDescription.isVisible = true
            llViewRecipeDescription.isVisible = false
            btnSaveRecipeDescription.setOnClickListener {
                val recipe = DummyRecipe(
                    id = args.recipe.id,
                    name = args.recipe.name,
                    description = itRecipeDescription.text.toString()
                )
                vm.editRecipeEntry(recipe)
                tvRecipeDescription.text = recipe.description
                llEditRecipeDescription.isVisible = false
                llViewRecipeDescription.isVisible = true
            }
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
}