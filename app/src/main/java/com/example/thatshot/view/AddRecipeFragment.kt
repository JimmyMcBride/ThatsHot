package com.example.thatshot.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.thatshot.R
import com.example.thatshot.adapter.IngredientListAdapter
import com.example.thatshot.databinding.ActivityMainBinding
import com.example.thatshot.databinding.FragmentAddRecipeBinding
import com.example.thatshot.repo.models.DummyIngredient


class AddRecipeFragment : Fragment() {
    private var _binding: FragmentAddRecipeBinding? = null
    private val binding get() = _binding!!
//    private val viewModel by viewModel<>()

    private var ingredients = mutableListOf<DummyIngredient>()

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
            findNavController().navigateUp()
        }
        btnAddIngredient.setOnClickListener {
            ingredients.add(
                DummyIngredient(
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
    }
}