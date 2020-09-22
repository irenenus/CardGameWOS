package com.example.warofsuits.ui.game

import android.content.Context
import android.util.Log
import com.example.warofsuits.model.Card
import com.example.warofsuits.model.Suit


class GamePresenterImpl(context: Context, private val view: GameView) : GamePresenter {

    private val cardsInteractor = CardsInteractor(context)

    private var discardCount1 = 0
    private var discardCount2 = 0
    private var round = 0
    private var cardsList = mutableListOf<Card>()
    private var player1List = mutableListOf<Card>()
    private var player2List = mutableListOf<Card>()
    private var suitsList = mutableListOf<Suit>()
    private var winnerRoundP1 = true

    override fun onCreateGame(){
        refresh()
    }

    override fun onLayDownCardPlayer1() {
        view.onLayDownCardPlayer1(player1List[round].image)
    }

    override fun onLayDownCardPlayer2() {
        view.onLayDownCardPlayer2(player2List[round].image)
    }

    override fun getWinner() : Boolean? {

        if(view.onBothLayDownDone()){
           when {
               //Compare the values
               player1List[round].value > player2List[round].value -> {
                   discardCount1+=2
                   updateVarsAndCheckFinal()

                   return true
               }
               player1List[round].value < player2List[round].value -> {
                   discardCount2+=2
                   updateVarsAndCheckFinal()

                   return false
               }

               //if values are equal compare suits
               else -> {
                   //create a list only with the id Suits sorted by their value (higher to lower)
                   val suitsIdList = mutableListOf<Char>()
                   suitsList.forEach {
                       suitsIdList.add(it.id)
                   }
                   //compare which suit valor is higher
                   winnerRoundP1 = suitsIdList.indexOf(player1List[round].suit) < suitsIdList.indexOf(player2List[round].suit)

                   if(winnerRoundP1){
                       discardCount1+=2
                   }
                   else {
                       discardCount2+=2
                   }

                   updateVarsAndCheckFinal()
                   return winnerRoundP1

               }
           }
        }
        return null
    }

    private fun updateVarsAndCheckFinal(){
        round++

        //update the discard numbers
        view.setActionDiscardCountText(discardCount1, discardCount2)
        checkFinalRound()
    }

    private fun checkFinalRound(){
        val totalDiscard = discardCount1 + discardCount2

        if(totalDiscard == cardsList.size){
            Log.d("Finish", "The game has finished")
            view.onFinishGame(discardCount1, discardCount2)
        }
    }

    private fun refresh() {
        //reset counter of discarded cards
        discardCount1 = 0
        discardCount2 = 0
        round = 0

        //update in the ui the discard numbers
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
        suitsList = cardsInteractor.createSuits()

        //Mix suits randomly
        suitsList.shuffle()
        view.showSuitsOrder(suitsList)

    }
}