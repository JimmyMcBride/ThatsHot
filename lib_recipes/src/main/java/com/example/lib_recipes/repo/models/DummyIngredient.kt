package com.example.lib_recipes.repo.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity(tableName = "dummy_ingredients")
data class DummyIngredient(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val recipe: Int,
    val name: String,
    val amount: Double,
    val unit: String,
): Serializable