package com.example.thatshot.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thatshot.adapter.IngredientListAdapter
import com.example.thatshot.adapter.RecipeListAdapter
import com.example.thatshot.databinding.FragmentEditRecipeBinding
import com.example.thatshot.repo.implementation.RecipeImplement
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.utils.Resource
import com.example.thatshot.viewmodels.CreateRecipeViewmodel
import com.example.thatshot.viewmodels.RecipeListViewmodel
import com.example.thatshot.viewmodels.SingleRecipeViewmodel
import com.example.thatshot.viewmodels.factories.ViewModelFactoryCreate
import com.example.thatshot.viewmodels.factories.ViewModelFactroyRecipeList
import com.example.thatshot.viewmodels.factories.ViewModelFactroySingle
import com.example.thatshot.viewmodels.factories.description

class EditRecipeFragment : Fragment() {
    private var _binding: FragmentEditRecipeBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<EditRecipeFragmentArgs>()
    private var recipes = listOf<DummyRecipe>()

    //    private val viewModel by viewModel<>()
    private val repoImp by lazy {
        RecipeImplement(requireContext())
    }

    private val viewModel by viewModels<CreateRecipeViewmodel>() {
        ViewModelFactoryCreate(repoImp)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentEditRecipeBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObser()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObser() = with(binding) {
        var response = listOf<DummyRecipe>()

//       rvIngredients.adapter = IngredientListAdapter(args.recipe.ingredients, true)

        tvRecipeName.text = args.recipe.name
        tvRecipeDescription.text=args.recipe.description


        binding.itRecipeName.addTextChangedListener { it ->
            viewModel.name = it.toString()
            tvRecipeName.text = it.toString()
        }
        itRecipeDescription.addTextChangedListener { it ->
            viewModel.description = it.toString()
            tvRecipeDescription.text = it.toString()
        }
        Log.d("id", viewModel.name)
        Log.d("id", viewModel.description)
    }

    private fun initViews() = with(binding) {
//        rvIngredients.adapter = RecipeListAdapter(recipes, ::navigate)
//
//        rvIngredients.adapter = RecipeListAdapter(recipes, ::navigate)
        btnSaveRecipeName.setOnClickListener {
            llEditRecipeName.isVisible = false
            llViewRecipeName.isVisible = true


        val todo =DummyRecipe(
                id = args.recipe.id,
                name = viewModel.name,
                description = args.recipe.description
        )
            viewModel.addTodoItem(todo)


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

            btnSaveRecipeDescription.setOnClickListener {
                llEditRecipeDescription.isVisible = false
                llViewRecipeDescription.isVisible = true

                val todo =DummyRecipe(
                    id = args.recipe.id,
                    name = viewModel.name,
                    description = viewModel.description
                )
                viewModel.addTodoItem(todo)


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

    private fun navigate(dummyRecipe: DummyRecipe) {
        TODO("Not yet implemented")
    }

