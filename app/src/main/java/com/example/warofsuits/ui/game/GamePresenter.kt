package com.example.warofsuits.ui.game

interface GamePresenter{

    fun onCreateGame()

    fun onLayDownCardPlayer1()

    fun onLayDownCardPlayer2()

    fun getWinner() : Boolean?

}