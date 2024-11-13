package com.agilbudiprasetyo.medicineslistapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Medicines(
    val name: String,
    val image: String,
    val description: String
): Parcelable
