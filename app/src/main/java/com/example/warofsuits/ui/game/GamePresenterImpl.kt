package com.example.warofsuits.ui.game

import android.content.Context
import android.util.Log
import com.example.warofsuits.model.Card
import com.example.warofsuits.model.Suit


class GamePresenterImpl(context: Context, private val view: GameView) : GamePresenter {

    private val cardsInteractor = CardsInteractor(context)

    private var discardCount1 = 0
    private var discardCount2 = 0
    var round = 0
    var cardsList = mutableListOf<Card>()
    var player1List = mutableListOf<Card>()
    var player2List = mutableListOf<Card>()
    var suitsList = mutableListOf<Suit>()
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
            return compareValues(player1List[round], player2List[round], suitsList)
        }
        return null
    }

    fun compareValues(card1: Card, card2 : Card, suitsList: MutableList<Suit>): Boolean {
        when {
            //Compare the values
            card1.value > card2.value -> {
                discardCount1+=2
                updateVarsAndCheckFinal(discardCount1, discardCount2, cardsList.size)

                return true
            }
            card1.value < card2.value -> {
                discardCount2+=2
                updateVarsAndCheckFinal(discardCount1, discardCount2, cardsList.size)

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
                winnerRoundP1 = suitsIdList.indexOf(card1.suit) < suitsIdList.indexOf(card2.suit)

                if(winnerRoundP1){
                    discardCount1+=2
                }
                else {
                    discardCount2+=2
                }

                updateVarsAndCheckFinal(discardCount1, discardCount2, cardsList.size)
                return winnerRoundP1

            }
        }
    }

    fun updateVarsAndCheckFinal(count1: Int, count2: Int, maxCards : Int){
        round++

        //update the discard numbers
        view.setActionDiscardCountText(count1, count2)
        val totalDiscard = count1 + count2

        if(totalDiscard == maxCards){
            Log.d("Finish", "The game has finished")
            view.onFinishGame(count1, count2)
        }
    }

    fun refresh() {
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