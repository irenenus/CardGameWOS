package com.example.warofsuits

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.example.warofsuits.model.Card
import com.example.warofsuits.model.Result
import com.example.warofsuits.model.Suit
import com.example.warofsuits.ui.game.CardsInteractor
import com.example.warofsuits.ui.game.GamePresenterImpl
import com.example.warofsuits.ui.game.GameView
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class RulesGameTest{
    private lateinit var context: Context
    private lateinit var gamePresenterImpl: GamePresenterImpl
    private lateinit var gameView: GameView
    private lateinit var suitsList: MutableList<Suit>

    @Before
    fun onCreateCardTest(){
        context = ApplicationProvider.getApplicationContext()

        //Create mock game class
        gameView = Mockito.mock(GameView::class.java)
        //Create class GamePresenterImpl
        gamePresenterImpl = GamePresenterImpl(context, gameView)

        //Create suitsList
        suitsList = CardsInteractor(context).createSuits()
    }

    @Test
    fun onCreateGame(){
        gamePresenterImpl.onCreateGame()

        //Verify setActionDiscardCountText
        verify(gameView).setActionDiscardCountText(0, 0)

        //Check if the first round is 0
        org.junit.Assert.assertEquals(0, gamePresenterImpl.round)

        //Check if each player has 26 cards in their list
        org.junit.Assert.assertEquals(26, gamePresenterImpl.player1List.size)
        org.junit.Assert.assertEquals(26, gamePresenterImpl.player2List.size)

        //if suitsList aren't equals, then gamePresenterImpl.suitsList is mixed
        org.junit.Assert.assertNotEquals(suitsList, gamePresenterImpl.suitsList)
    }

    @Test
    fun refresh(){
        gamePresenterImpl.refresh()

        //Verify setActionDiscardCountText
        verify(gameView).setActionDiscardCountText(0, 0)

        //Check if the first round is 0
        org.junit.Assert.assertEquals(0, gamePresenterImpl.round)

        //Check if each player has 26 cards in their list
        org.junit.Assert.assertEquals(26, gamePresenterImpl.player1List.size)
        org.junit.Assert.assertEquals(26, gamePresenterImpl.player2List.size)

        //if suitsList aren't equals, then gamePresenterImpl.suitsList is mixed
        org.junit.Assert.assertNotEquals(suitsList, gamePresenterImpl.suitsList)
    }

    @Test
    fun cardListIsShuffled(){
        //if this statement is true, the cardList is mixed
        org.junit.Assert.assertNotEquals(CardsInteractor(context).createCards(), gamePresenterImpl.cardsList)
    }

    @Test
    fun updateVarsAndCheckFinal(){
        //check if gameView implements setActionDiscardCountText when updateVarsAndCheckFinal function is called
        gamePresenterImpl.updateVarsAndCheckFinal(2,3, 52)
        verify(gameView).setActionDiscardCountText(2,3)
    }


    @Test @Ignore("Test deprecated")
    fun checkFinalRound(){
        //check if gameView implements setActionDiscardCountText when updateVarsAndCheckFinal function is called and the number of discard cards is max
        gamePresenterImpl.updateVarsAndCheckFinal(25,27, 52)
        //verify(gameView).onFinishGame(25,27)
    }

    @Test
    fun checkFinalRound2(){
        gameView.onFinishGame(result = Result(22, 30, 26, 22/2, 30/2, false))
    }

    @Test
    fun winner2(){
        //check if player 2 can win if suits order is: 'S', 'D', 'C', 'H' and values: 2 < 4
        val player1IsTheWinner = gamePresenterImpl.compareValues(
            Card("s2", 2, 'S', context.getDrawable(R.drawable.s2)!!),
            Card("d4", 4, 'D', context.getDrawable(R.drawable.d4)!!),
            suitsList)

        org.junit.Assert.assertEquals(false, player1IsTheWinner)
    }

    @Test
    fun winner1(){
        //check if player 1 can win if suits order is: 'S', 'D', 'C', 'H' and values: 10 > 4
        val player1IsTheWinner = gamePresenterImpl.compareValues(
            Card("s10", 10, 'S', context.getDrawable(R.drawable.s10)!!),
            Card("d4", 4, 'D', context.getDrawable(R.drawable.d4)!!),
            suitsList)

        org.junit.Assert.assertEquals(true, player1IsTheWinner)
    }

    @Test
    fun winner1SameValues(){
        //check if player 1 can win with same values and different suits: S > D
        val player1IsTheWinner = gamePresenterImpl.compareValues(
            Card("s10", 10, 'S', context.getDrawable(R.drawable.s10)!!),
            Card("d10", 10, 'D', context.getDrawable(R.drawable.d10)!!),
            suitsList)

        org.junit.Assert.assertEquals(true, player1IsTheWinner)
    }

    @Test
    fun winner1SameValues2(){
        //check if player 1 can win with same values and different suits: S > H
        val player1IsTheWinner = gamePresenterImpl.compareValues(
            Card("s5", 5, 'S', context.getDrawable(R.drawable.s5)!!),
            Card("h5", 5, 'H', context.getDrawable(R.drawable.h5)!!),
            suitsList)

        org.junit.Assert.assertEquals(true, player1IsTheWinner)
    }


    @Test
    fun winner2SameValues(){
        //check if player 2 can win with same values and different suits: H < C
        val player1IsTheWinner = gamePresenterImpl.compareValues(
            Card("h5", 5, 'H', context.getDrawable(R.drawable.h5)!!),
            Card("c5", 5, 'C', context.getDrawable(R.drawable.c5)!!),
            suitsList)

        org.junit.Assert.assertEquals(false, player1IsTheWinner)
    }

    @Test
    fun winner2SameValues2(){
        //check if player 2 can win with same values and different suits: C < S
        val player1IsTheWinner = gamePresenterImpl.compareValues(
            Card("c5", 5, 'C', context.getDrawable(R.drawable.c5)!!),
            Card("s5", 5, 'S', context.getDrawable(R.drawable.s5)!!),
            suitsList)

        org.junit.Assert.assertEquals(false, player1IsTheWinner)
    }

    @Test
    fun getWinner(){
        //Check if the two players have layed down their top cards before doing the comparison
        gamePresenterImpl.getWinner()
        verify(gameView).onBothLayDownDone()
    }


}