package com.example.thatshot.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thatshot.views.adapter.IngredientListAdapter
import com.example.thatshot.databinding.FragmentAddRecipeBinding
import com.example.thatshot.model.models.Ingredient
import com.example.thatshot.model.models.Recipe
import com.example.thatshot.model.repo.RepoImpl
import com.example.thatshot.viewmodels.AddRecipeViewModel
import com.example.thatshot.viewmodels.factories.ViewModelFactoryAddRecipe


class AddRecipeFragment : Fragment() {
    private var _binding: FragmentAddRecipeBinding? = null
    private val binding get() = _binding!!

//    private var ingredients = mutableListOf<Ingredient>()

    private val repo by lazy {
        RepoImpl(requireContext())
    }
    val viewModel by viewModels<AddRecipeViewModel> {
        ViewModelFactoryAddRecipe(repo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentAddRecipeBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true) //Set this to true in order to trigger callbacks to Fragment#onOptionsItemSelected

        initViews()
    }

    override fun onDestroyView() {
//        backPressedDispatcher.remove()
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() = with(binding) {
//        rvIngredients.adapter = IngredientListAdapter(ingredients, false)
        btnSaveRecipe.setOnClickListener {
            val name = this.itRecipeName.text.toString()
            val description = this.itRecipeDescription.text.toString()
            if (name == "" || description == "") return@setOnClickListener
            viewModel.addRecipe(
                Recipe(
                    name = name,
                    description = description
                )
            )

            findNavController().navigateUp()
        }
//        btnAddIngredient.setOnClickListener {
//            ingredients.add(
//                Ingredient(
//                    1,
//                    itIngredientName.text.toString(),
//                    itIngredientAmount.text.toString().toDouble(),
//                    itIngredientUnit.text.toString()
//                )
//            )
//            itIngredientName.text?.clear()
//            itIngredientAmount.text?.clear()
//            itIngredientUnit.text?.clear()
//            rvIngredients.adapter = IngredientListAdapter(ingredients, false)
//        }
    }
}