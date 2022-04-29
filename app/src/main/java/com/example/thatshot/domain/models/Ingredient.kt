package com.example.thatshot.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "ingredients_table")
@Parcelize
data class Ingredient(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val recipe: Int,
    val name: String,
    val amount: Double,
    val unit: String,
): Parcelable