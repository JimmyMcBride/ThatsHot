package com.example.thatshot.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
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
import com.example.thatshot.viewmodels.AddRecipeViewModel
import com.example.thatshot.viewmodels.factories.ViewModelFactoryAddRecipe


class AddRecipeFragment : Fragment() {

    private var _binding: FragmentAddRecipeBinding? = null
    private val binding: FragmentAddRecipeBinding get() = _binding!!

    val repoImpl by lazy {
        RepoImpl(requireContext())
    }

    val vm by viewModels<AddRecipeViewModel> {
        ViewModelFactoryAddRecipe(repoImpl)
    }

//    private var ingredients = mutableListOf<DummyIngredient>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAddRecipeBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

            val name = itRecipeName.text.toString()
            val description = itRecipeDescription.text.toString()
            val recipe = DummyRecipe(
                name = name,
                description = description
            )
            vm.addRecipe(recipe)

            findNavController().navigateUp()

        }

//        btnAddIngredient.setOnClickListener {
//            ingredients.add(
//                DummyIngredient(
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