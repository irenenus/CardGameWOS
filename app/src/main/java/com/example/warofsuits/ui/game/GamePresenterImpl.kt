package com.example.warofsuits.ui.game

import android.content.Context
import com.example.warofsuits.model.Card


class GamePresenterImpl(private val context: Context, private val view: GameView) : GamePresenter {

    private val cardsInteractor = CardsInteractor(context)

    private var discardCount1 = 0
    private var discardCount2 = 0
    private var cardsList = mutableListOf<Card>()
    private var player1List = mutableListOf<Card>()
    private var player2List = mutableListOf<Card>()

    override fun onCreateGame(){
        refresh()
    }

    override fun onRestartGame() {
        refresh()
    }

    private fun refresh() {
        //reset counter of discarded cards
        discardCount1 = 0
        discardCount2 = 0
        view.setActionDiscardCountText(discardCount1, discardCount2)

        updateGameField()
    }

    private fun updateGameField() {
        //Get the sorted list of poker cards
        cardsList = cardsInteractor.createCards()

        //Mix cardsList randomly
        cardsList.shuffle()

        //Split the list in 2 parts, one for each player
        player1List = cardsList.subList(0, (cardsList.size+1)/2)
        player2List = cardsList.subList((cardsList.size+1)/2, cardsList.size)

        //Create the List of suits
        val suitsList = cardsInteractor.createSuits()

        //Mix suits randomly
        suitsList.shuffle()
        view.showSuitsOrder(suitsList)

    }
}