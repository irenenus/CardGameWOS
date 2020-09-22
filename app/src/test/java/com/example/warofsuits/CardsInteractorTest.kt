package com.example.warofsuits


import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.example.warofsuits.ui.game.CardsInteractor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith (RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class CardTest {
    private lateinit var context: Context
    private lateinit var cardsInteractor : CardsInteractor

    @Before
    fun onCreateCardTest(){
        //Create CardsInteractor
        context = ApplicationProvider.getApplicationContext()
        cardsInteractor = CardsInteractor(context)
    }
    @Test
    fun canCreateCardList() {
        //Create CardList
        cardsInteractor.createCards()
    }

    @Test
    fun getCardsSize() {
        //Test if cardList size is 52
        org.junit.Assert.assertEquals(52, cardsInteractor.createCards().size)
    }

    @Test

    fun getSuitCards(){
        // Match 13 with a list of Cards of each suit to verify that there are 4 suits with 13 cards each one

        //Filter the card list by suit
        val hCards = cardsInteractor.createCards().filter { it.suit == 'H' }
        val sCards = cardsInteractor.createCards().filter { it.suit == 'S' }
        val cCards = cardsInteractor.createCards().filter { it.suit == 'C' }
        val dCards = cardsInteractor.createCards().filter { it.suit == 'D' }

        //Compare 13 with the size of the lists
        org.junit.Assert.assertEquals(13, hCards.size)
        org.junit.Assert.assertEquals(13, sCards.size)
        org.junit.Assert.assertEquals(13, cCards.size)
        org.junit.Assert.assertEquals(13, dCards.size)
    }

    @Test
    fun canCreateSuitsList() {
        //Check if the suite list size is 4
        cardsInteractor.createSuits()
        org.junit.Assert.assertEquals(4, cardsInteractor.createSuits().size)
    }

}