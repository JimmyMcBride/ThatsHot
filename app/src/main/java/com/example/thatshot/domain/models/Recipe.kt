package com.example.thatshot.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "recipe_table")
@Parcelize
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val recipeId: Int,
    val recipe: String,
    val description: String,
//    @TypeConverters(DBConverter::class)
//    val ingredients: List<Ingredient>
) : Parcelable

