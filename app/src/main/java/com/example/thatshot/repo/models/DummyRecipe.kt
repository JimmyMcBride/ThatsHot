package com.example.thatshot.repo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DummyRecipe(
    val id: Int,
    val name: String,
    val description: String,
    val ingredients: List<DummyIngredient>
) : Parcelable


