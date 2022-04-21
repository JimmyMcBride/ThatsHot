package com.example.thatshot.repo.models

import java.io.Serializable

data class DummyIngredient(
    val id: Int,
    val name: String,
    val amount: Double,
    val unit: String,
) : Serializable