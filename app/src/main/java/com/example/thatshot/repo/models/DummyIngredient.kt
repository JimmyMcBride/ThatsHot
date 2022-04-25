package com.example.thatshot.repo.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Entity
@Parcelize
data class DummyIngredient(
    @PrimaryKey val id: Int,
    val name: String,
    val amount: Double,
    val unit: String,
) : Parcelable