package com.example.warofsuits.model

import java.io.Serializable

data class Result(
    val discardCounter1: Int,
    val discardCounter2: Int,
    val totalRounds: Int,
    val numRounds1: Int,
    val numRounds2: Int,
    val winner1: Boolean?
) : Serializable