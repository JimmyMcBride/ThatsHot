package com.example.thatshot.model.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "recipes")
@Parcelize
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0 ,
    val name: String,
    val description: String,
//    val ingredients: List<Ingredient>
) : Parcelable


