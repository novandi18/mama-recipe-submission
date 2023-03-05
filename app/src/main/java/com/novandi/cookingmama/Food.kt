package com.novandi.cookingmama

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val name: String,
    val description: String,
    val photo: Int,
    val ingredient: String,
    val step: String
) : Parcelable
