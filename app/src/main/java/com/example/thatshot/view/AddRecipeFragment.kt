package com.example.thatshot.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.thatshot.adapter.IngredientListAdapter
import com.example.thatshot.databinding.FragmentAddRecipeBinding
import com.example.thatshot.repo.ThatsHotRepoImplementation
import com.example.thatshot.repo.models.Ingredient
import com.example.thatshot.repo.models.Recipe
import com.example.thatshot.util.StateResource
import com.example.thatshot.viewmodel.RecipeListViewModel
import com.example.thatshot.viewmodel.RecipeListViewmodelFactory


class AddRecipeFragment : Fragment() {
    private var _binding: FragmentAddRecipeBinding? = null
    private val binding get() = _binding!!
    private val repo by lazy {
        ThatsHotRepoImplementation(requireContext())
    }
    private val viewModel by lazy {
        ViewModelProvider(this, RecipeListViewmodelFactory(repo))[RecipeListViewModel::class.java]
    }

    private var ingredients = mutableListOf<Ingredient>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentAddRecipeBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        rvIngredients.adapter = IngredientListAdapter(ingredients, false)
        btnSaveRecipe.setOnClickListener {
            val newRecipe = Recipe(
                name = itRecipeName.text.toString(),
                description = itRecipeDescription.text.toString(),
                ingredients = mutableListOf()
            )
            viewModel.addRecipe(newRecipe)
        }
        btnAddIngredient.setOnClickListener {
            ingredients.add(
                Ingredient(
                    1,
                    itIngredientName.text.toString(),
                    itIngredientAmount.text.toString().toDouble(),
                    itIngredientUnit.text.toString()
                )
            )
            itIngredientName.text?.clear()
            itIngredientAmount.text?.clear()
            itIngredientUnit.text?.clear()
            rvIngredients.adapter = IngredientListAdapter(ingredients, false)
        }

        viewModel.state.observe(viewLifecycleOwner) {
            when(it){
                is StateResource.Error -> {
                    Log.d("ADD_FRAGMENT", it.err.toString())
                    viewModel.setStandby()
                }
                is StateResource.Loading -> {
                    disableButtons()
                }
                is StateResource.Standby -> {
                    enableButtons()
                }
                is StateResource.Success -> {
                    viewModel.setStandby()
                    findNavController().navigateUp()
                }
            }
        }
    }

    private fun enableButtons() = with(binding) {
        btnAddIngredient.isEnabled = true
        btnSaveRecipe.isEnabled = true
    }

    private fun disableButtons() = with(binding) {
        btnAddIngredient.isEnabled = false
        btnSaveRecipe.isEnabled = false
    }
}