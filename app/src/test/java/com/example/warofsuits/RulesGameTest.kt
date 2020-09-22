package com.example.warofsuits

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.example.warofsuits.model.Card
import com.example.warofsuits.model.Suit
import com.example.warofsuits.ui.game.CardsInteractor
import com.example.warofsuits.ui.game.GamePresenterImpl
import com.example.warofsuits.ui.game.GameView
import org.junit.Before
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
        gameView = Mockito.mock(GameView::class.java)
        gamePresenterImpl = GamePresenterImpl(context, gameView)

        suitsList = CardsInteractor(context).createSuits()
    }

    @Test
    fun onCreateGame(){

        gamePresenterImpl.onCreateGame()

        verify(gameView).setActionDiscardCountText(0, 0)
        org.junit.Assert.assertEquals(0, gamePresenterImpl.round)
        org.junit.Assert.assertEquals(26, gamePresenterImpl.player1List.size)
        org.junit.Assert.assertEquals(26, gamePresenterImpl.player2List.size)
        //if suitsList aren't equals, then gamePresenterImpl.suitsList is mixed
        org.junit.Assert.assertNotEquals(suitsList, gamePresenterImpl.suitsList)
    }

    @Test
    fun refresh(){
        gamePresenterImpl.refresh()

        verify(gameView).setActionDiscardCountText(0, 0)
        org.junit.Assert.assertEquals(0, gamePresenterImpl.round)
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
        gamePresenterImpl.updateVarsAndCheckFinal(2,3, 52)
        verify(gameView).setActionDiscardCountText(2,3)
    }

    @Test
    fun checkFinalRound(){
        gamePresenterImpl.updateVarsAndCheckFinal(25,27, 52)
        verify(gameView).onFinishGame(25,27)
    }

    @Test
    fun winner2(){
        val player1IsTheWinner = gamePresenterImpl.compareValues(
            Card("s2", 2, 'S', context.getDrawable(R.drawable.s2)!!),
            Card("d4", 4, 'D', context.getDrawable(R.drawable.d4)!!),
            suitsList)

        org.junit.Assert.assertEquals(false, player1IsTheWinner)
    }

    @Test
    fun winner1(){
        val player1IsTheWinner = gamePresenterImpl.compareValues(
            Card("s10", 10, 'S', context.getDrawable(R.drawable.s10)!!),
            Card("d4", 4, 'D', context.getDrawable(R.drawable.d4)!!),
            suitsList)

        org.junit.Assert.assertEquals(true, player1IsTheWinner)
    }

    @Test
    fun winner1SameValues(){
        val player1IsTheWinner = gamePresenterImpl.compareValues(
            Card("s10", 10, 'S', context.getDrawable(R.drawable.s10)!!),
            Card("d10", 10, 'D', context.getDrawable(R.drawable.d10)!!),
            suitsList)

        org.junit.Assert.assertEquals(true, player1IsTheWinner)
    }

    @Test
    fun winner1SameValues2(){
        val player1IsTheWinner = gamePresenterImpl.compareValues(
            Card("s5", 5, 'S', context.getDrawable(R.drawable.s5)!!),
            Card("h5", 5, 'H', context.getDrawable(R.drawable.h5)!!),
            suitsList)

        org.junit.Assert.assertEquals(true, player1IsTheWinner)
    }


    @Test
    fun winner2SameValues(){
        val player1IsTheWinner = gamePresenterImpl.compareValues(
            Card("h5", 5, 'H', context.getDrawable(R.drawable.h5)!!),
            Card("c5", 5, 'C', context.getDrawable(R.drawable.c5)!!),
            suitsList)

        org.junit.Assert.assertEquals(false, player1IsTheWinner)
    }

    @Test
    fun winner2SameValues2(){
        val player1IsTheWinner = gamePresenterImpl.compareValues(
            Card("c5", 5, 'C', context.getDrawable(R.drawable.h5)!!),
            Card("s5", 5, 'S', context.getDrawable(R.drawable.s5)!!),
            suitsList)

        org.junit.Assert.assertEquals(false, player1IsTheWinner)
    }

    @Test
    fun getWinner(){
        gamePresenterImpl.getWinner()
        verify(gameView).onBothLayDownDone()
    }


}