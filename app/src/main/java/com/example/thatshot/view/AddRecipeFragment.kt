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
import com.example.lib_recipes.repo.RepoImpl
import com.example.lib_recipes.repo.models.DummyIngredient
import com.example.lib_recipes.repo.models.DummyRecipe
import com.example.lib_recipes.util.Resource
import com.example.thatshot.viewholder.AddRecipeViewModel
import com.example.thatshot.viewholder.factory.ViewModelFactoryAddRecipe
import kotlin.math.log


class AddRecipeFragment : Fragment() {
    private var _binding: FragmentAddRecipeBinding? = null
    private val binding get() = _binding!!

    val repoImp by lazy {
        com.example.lib_recipes.repo.RepoImpl(requireContext())
    }

    private val viewModel by viewModels<AddRecipeViewModel>() {
        ViewModelFactoryAddRecipe(repoImp)
    }

    val args by navArgs<AddRecipeFragmentArgs>()
    private var ingredients = mutableListOf<com.example.lib_recipes.repo.models.DummyIngredient>()

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

//        (requireActivity() as AppCompatActivity).apply {
//            // Redirect system "Back" press to our dispatcher
//            onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedDispatcher)
//
//            // Set toolbar if it is in Fragment's layout. If you have a global toolbar that lives in Activity layout, then you don't need this line.
//            setSupportActionBar(ActivityMainBinding.toolbar)
//
//            // Setup action bar to work with NavController
//            setupActionBarWithNavController(findNavController())
//        }


        initViews()
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return if (item.itemId == android.R.id.home) {
//            // Redirect "Up/Home" button clicks to our own function
//            this@AddRecipeFragment.onBackPressed()
//            true
//        } else {
//            super.onOptionsItemSelected(item)
//        }
//    }
//
//    private fun onBackPressed() {
//        // Work your magic! Show dialog etc.
//    }

    override fun onDestroyView() {
//        backPressedDispatcher.remove()
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() = with(binding) {
        var recipeList = listOf<com.example.lib_recipes.repo.models.DummyRecipe>()
        rvIngredients.adapter = IngredientListAdapter(ingredients, false)

//        HERE IS ALL OF RECIPE LOGIC
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
                    com.example.lib_recipes.repo.models.DummyRecipe(
                        recipeId = args.recipeId,
                        recipe = itRecipeName.text.toString(),
                        description = itRecipeDescription.text.toString()
                    )
                )
                ingredients.forEach{ ingredient ->
                viewModel.addIngredient(
                    com.example.lib_recipes.repo.models.DummyIngredient(
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
                is com.example.lib_recipes.util.Resource.Error -> {
                    Toast.makeText(context, recipeViewState.message, Toast.LENGTH_SHORT).show()
                }
                is com.example.lib_recipes.util.Resource.Success -> {
                    recipeList = recipeViewState.data
                }
                is com.example.lib_recipes.util.Resource.Loading -> {}
                else -> {}
            }

        }
        //HERE IS START OF INGREDIENTS LOGIC
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
                com.example.lib_recipes.repo.models.DummyIngredient(
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

    fun recipeExists(recipes: List<com.example.lib_recipes.repo.models.DummyRecipe>): Boolean {
        var flag = false
        recipes.forEach { recipe ->
            if (binding.itRecipeName.toString() == recipe.recipe) {
                flag = true
            }
        }
        return flag
    }
}