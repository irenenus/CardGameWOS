package com.example.warofsuits.ui.game

interface GamePresenter{

    fun onCreateGame()

    fun onRestartGame()

    fun onLayDownCardPlayer1()

    fun onLayDownCardPlayer2()

    fun getWinner() : Boolean?

}