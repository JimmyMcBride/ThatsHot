package com.example.thatshot.model.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "ingredients")
@Parcelize
data class Ingredient(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val recipeId: Int,
    val name: String,
    val amount: Double,
    val unit: String,
) : Parcelable