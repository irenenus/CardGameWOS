package com.example.warofsuits.ui.game

import com.example.warofsuits.model.Suit

interface GameView {

    fun setActionDiscardCountText(numDiscardedCards1 : Int, numDiscardedCards2 : Int)

    fun showSuitsOrder(suitList: MutableList<Suit>)

}