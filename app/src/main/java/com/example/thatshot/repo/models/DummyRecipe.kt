package com.example.thatshot.repo.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "dummy_recipe")
@Parcelize
data class DummyRecipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val recipeId: Int,
    val recipe: String,
    val description: String,
//    val ingredients: String
) : Parcelable

