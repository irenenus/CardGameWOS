package com.example.warofsuits.ui.result

import android.content.Context
import com.example.warofsuits.R

class ResultPresenterImpl(private val context: Context) :
    ResultPresenter {

    override fun getStringWinner(winner1: Boolean?): String {

        return when (winner1){
            true -> context.getString(R.string.player1_winner_game)
            false -> context.getString(R.string.player2_winner_game)
            else -> context.getString(R.string.no_winner_game)
        }
    }

}