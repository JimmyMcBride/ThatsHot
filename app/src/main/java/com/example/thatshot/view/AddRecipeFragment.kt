package com.example.thatshot.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.thatshot.R
import com.example.thatshot.adapter.IngredientListAdapter
import com.example.thatshot.databinding.ActivityMainBinding
import com.example.thatshot.databinding.FragmentAddRecipeBinding
import com.example.thatshot.repo.RepoImpl
import com.example.thatshot.repo.models.DummyIngredient
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.util.Resource
import com.example.thatshot.viewholder.AddRecipeViewModel
import com.example.thatshot.viewholder.factory.ViewModelFactoryAddRecipe
import kotlin.math.log


class AddRecipeFragment : Fragment() {
    private var _binding: FragmentAddRecipeBinding? = null
    private val binding get() = _binding!!

    val repoImp by lazy {
        RepoImpl(requireContext())
    }

    private val viewModel by viewModels<AddRecipeViewModel>() {
        ViewModelFactoryAddRecipe(repoImp)
    }

    val args by navArgs<AddRecipeFragmentArgs>()
    private var ingredients = mutableListOf<DummyIngredient>()

    private var ingredientId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentAddRecipeBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllRecipes()
        setHasOptionsMenu(true) //Set this to true in order to trigger callbacks to Fragment#onOptionsItemSelected


        initViews()
    }


    override fun onDestroyView() {
//        backPressedDispatcher.remove()
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() = with(binding) {
        var recipeList = listOf<DummyRecipe>()
        rvIngredients.adapter = IngredientListAdapter(ingredients, false)

        viewModel.saveBtn.observe(viewLifecycleOwner) { btnSwitch ->
            btnSaveRecipe.isEnabled = btnSwitch
        }
        itRecipeName.addTextChangedListener {
            viewModel.recipe = it.toString()
            validateRecipe()
            validateIngredients()
        }
        itRecipeDescription.addTextChangedListener {
            viewModel.description = it.toString()
            validateRecipe()
        }
        btnSaveRecipe.setOnClickListener {
            val isRecipeMade = recipeExists(recipeList)
            if (!isRecipeMade) {
                viewModel.addRecipe(
                    DummyRecipe(
                        recipeId = args.recipeId,
                        recipe = itRecipeName.text.toString(),
                        description = itRecipeDescription.text.toString()
                    )
                )
                ingredients.forEach{ ingredient ->
                    viewModel.addIngredient(
                        DummyIngredient(
                            recipe = args.recipeId,
                            name = ingredient.name,
                            amount = ingredient.amount,
                            unit = ingredient.unit
                        )
                    )

                }
            }
            findNavController().navigateUp()
        }

        viewModel.allRecipes.observe(viewLifecycleOwner) { recipeViewState ->
            when (recipeViewState) {
                is Resource.Error -> {
                    Toast.makeText(context, recipeViewState.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    recipeList = recipeViewState.data
                }
                is Resource.Loading -> {}
                else -> {}
            }

        }

        viewModel.saveIngredient.observe(viewLifecycleOwner) { btnSwitch ->
            btnAddIngredient.isEnabled = btnSwitch
        }
        itIngredientName.addTextChangedListener {
            viewModel.ingredientName = it.toString()
            validateIngredients()
        }
        itIngredientAmount.addTextChangedListener {
            viewModel.ingredientAmount = it.toString()
            validateIngredients()
        }
        itIngredientUnit.addTextChangedListener {
            viewModel.ingredientUnit = it.toString()
            validateIngredients()
        }



        btnAddIngredient.setOnClickListener {
            ingredients.add(
                DummyIngredient(
                    id = ingredientId++,
                    recipe = args.recipeId,
                    name = itIngredientName.text.toString(),
                    amount = itIngredientAmount.text.toString().toDouble(),
                    unit = itIngredientUnit.text.toString()
                )
            )
            itIngredientName.text?.clear()
            itIngredientAmount.text?.clear()
            itIngredientUnit.text?.clear()
            rvIngredients.adapter = IngredientListAdapter(ingredients, false)
        }
    }

    private fun validateRecipe() {
        if (viewModel.recipe.isNotEmpty() && viewModel.description.isNotEmpty()) {
            viewModel.toggleSaveBtn(true)
        } else {
            viewModel.toggleSaveBtn(false)
        }
    }

    private fun validateIngredients() {
        if (viewModel.ingredientName.isNotEmpty() && viewModel.ingredientAmount.isNotEmpty() && viewModel.ingredientUnit.isNotEmpty()) {
            viewModel.toggleIngredientBtn(true)
        } else {
            viewModel.toggleIngredientBtn(false)
        }
    }

    fun recipeExists(recipes: List<DummyRecipe>): Boolean {
        var flag = false
        recipes.forEach { recipe ->
            if (binding.itRecipeName.toString() == recipe.recipe) {
                flag = true
            }
        }
        return flag
    }
}