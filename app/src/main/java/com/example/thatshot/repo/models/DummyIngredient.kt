package com.example.thatshot.repo.models

import androidx.room.PrimaryKey
import java.io.Serializable

data class DummyIngredient(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val amount: Double,
    val unit: String
) : Serializable