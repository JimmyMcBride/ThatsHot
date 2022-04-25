package com.example.thatshot.repo.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "recipe_entry")
@Parcelize
data class DummyRecipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
//    val ingredients: List<DummyIngredient>
) : Parcelable


