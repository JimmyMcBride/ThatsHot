package com.example.thatshot.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredient(
    val id: Int,
    val name: String,
    val amount: Double,
    val unit: String,
) : Parcelable