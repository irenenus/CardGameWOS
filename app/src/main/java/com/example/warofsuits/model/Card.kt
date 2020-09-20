package com.example.warofsuits.model

import android.graphics.drawable.Drawable

data class Card (
    val id: String,
    val value: Int,
    val suit: Char,
    val image: Drawable
)