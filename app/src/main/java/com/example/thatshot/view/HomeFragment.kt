package com.example.thatshot.view

import OnSwipeTouchListener
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thatshot.R
import com.example.thatshot.adapter.RecipeListAdapter
import com.example.thatshot.databinding.FragmentHomeBinding
import com.example.thatshot.repo.implementation.RecipeImplement
import com.example.thatshot.repo.models.DummyRecipe
import com.example.thatshot.utils.Resource
import com.example.thatshot.viewmodels.RecipeListViewmodel
import com.example.thatshot.viewmodels.factories.ViewModelFactroyRecipeList
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val repoImp by lazy {
        RecipeImplement(requireContext())
    }

    private val viewModel by viewModels<RecipeListViewmodel>() {
        ViewModelFactroyRecipeList(repoImp)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        activity?.actionBar?.title = getString(R.string.app_name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun initViews() = with(binding) {
        viewModel.grabAllRecipes()
        btnAddRecipe.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.goToAddRecipeFragment())
        }
        viewModel.todos.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is Resource.Error -> {
                    Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    Toast.makeText(context,"Lauched",Toast.LENGTH_SHORT).show()

                    rvRecipes.adapter = RecipeListAdapter(viewState.data,::recipeSelected, ::deleteRecipe)
                }
            }
        }
    }

    private fun deleteRecipe(recipe: DummyRecipe){
        viewModel.deleteRecipe(recipe)
        viewModel.grabAllRecipes()
        Toast.makeText(context,"RecipeDeleted", Toast.LENGTH_SHORT).show()
    }

    private fun recipeSelected(recipe: DummyRecipe) = with(findNavController()) {
        navigate(HomeFragmentDirections.actionHomeFragmentToViewRecipeFragment(recipe))
    }
}