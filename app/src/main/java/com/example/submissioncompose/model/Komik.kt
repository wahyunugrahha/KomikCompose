package com.example.submissioncompose.model

import androidx.annotation.DrawableRes

data class Komik(
    val name: String,
    @DrawableRes val imageResource: Int,
    val description: String = "",
)
