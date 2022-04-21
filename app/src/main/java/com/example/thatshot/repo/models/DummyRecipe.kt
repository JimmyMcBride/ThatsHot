package com.example.thatshot.repo.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity(tableName = "recipeTable")
@Parcelize
class DummyRecipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var description: String
) : Parcelable
//
//@Parcelize
//data class DummyIngredient(
//    val name: String,
//    val amount: Double,
//    val unit: String,
//    @ColumnInfo(name = "ingredient") val theIncrendients: Int
//
//) : Parcelable
