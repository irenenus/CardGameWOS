package com.example.warofsuits

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
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

    @Before
    fun onCreateCardTest(){
        context = ApplicationProvider.getApplicationContext()
        gameView = Mockito.mock(GameView::class.java)
        gamePresenterImpl = GamePresenterImpl(context, gameView)
    }

    @Test
    fun getWinner(){
        gamePresenterImpl.getWinner()
        verify(gameView).onBothLayDownDone()
    }


}