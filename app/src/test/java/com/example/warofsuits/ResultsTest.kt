package com.example.warofsuits

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.example.warofsuits.ui.result.ResultPresenterImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ResultsTest {
    private lateinit var context: Context
    private lateinit var resultPresenterImpl: ResultPresenterImpl

    @Before
    fun onCreateCardTest() {
        context = ApplicationProvider.getApplicationContext()

        //Create class ResultPresenterImpl
        resultPresenterImpl = ResultPresenterImpl(context)

    }

    @Test
    fun getStringWinner() {

        val stringWinner = resultPresenterImpl.getStringWinner(true)
        org.junit.Assert.assertEquals(context.getString(R.string.player1_winner_game), stringWinner)

        val stringWinner2 = resultPresenterImpl.getStringWinner(false)
        org.junit.Assert.assertEquals(context.getString(R.string.player2_winner_game), stringWinner2)

        val stringNoWinner = resultPresenterImpl.getStringWinner(null)
        org.junit.Assert.assertEquals(context.getString(R.string.no_winner_game), stringNoWinner)
    }

}