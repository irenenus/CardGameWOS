package com.example.warofsuits.ui.game

import android.graphics.drawable.Drawable
import com.example.warofsuits.model.Suit

interface GameView {

    fun setActionDiscardCountText(numDiscardedCards1 : Int, numDiscardedCards2 : Int)

    fun showSuitsOrder(suitList: MutableList<Suit>)

    fun onLayDownCardPlayer1(drawableLayDownCardPlayer1 : Drawable)

    fun onLayDownCardPlayer2(drawableLayDownCardPlayer2 : Drawable)

    fun onBothLayDownDone(): Boolean

    fun onFinishGame(discardCounter1: Int, discardCounter2: Int)


}