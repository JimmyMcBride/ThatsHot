package com.example.thatshot.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thatshot.adapter.IngredientListAdapter
import com.example.thatshot.databinding.FragmentViewRecipeBinding
import com.example.thatshot.repo.implementation.RecipeImplement
import com.example.thatshot.repo.models.DummyIngredient
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.utils.Resource
import com.example.thatshot.viewmodels.SingleRecipeViewmodel
import com.example.thatshot.viewmodels.factories.ViewModelFactroySingle

class ViewRecipeFragment : Fragment() {
    var test = listOf<DummyIngredient>()
    private var _binding: FragmentViewRecipeBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ViewRecipeFragmentArgs>()

    private val repoImp by lazy {
        RecipeImplement(requireContext())
    }

    private val viewModel by viewModels<SingleRecipeViewmodel>() {
        ViewModelFactroySingle(repoImp)
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
        var response = listOf<DummyRecipe>()
        val myid = args.recipe.id

        viewModel.singleRecipe(myid)

        viewModel.todos.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is Resource.Error -> {
                    Toast.makeText(context,"Error", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    Toast.makeText(context,"Lauched", Toast.LENGTH_SHORT).show()
                    tvRecipeName.text = viewState.data[0].name
                    tvRecipeDescription.text = viewState.data[0].description

                }
            }
        }

        rvIngredients.adapter = IngredientListAdapter( test, false)
        btnEditRecipe.setOnClickListener {
            findNavController().navigate(ViewRecipeFragmentDirections.goToEditRecipeFragment(args.recipe))
        }
//view with Id from database
    }
}