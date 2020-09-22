package com.example.warofsuits


import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.example.warofsuits.ui.game.CardsInteractor
import junit.framework.Assert.assertEquals
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
        context = ApplicationProvider.getApplicationContext()
        cardsInteractor = CardsInteractor(context)
    }
    @Test
    fun canCreateCardList() {
        cardsInteractor.createCards()
    }

    @Test
    fun getCardsSize() {
        assertEquals(52, cardsInteractor.createCards().size)
    }

    @Test
    fun canCreateSuitsList() {
        cardsInteractor.createSuits()
        assertEquals(4, cardsInteractor.createSuits().size)
    }

}