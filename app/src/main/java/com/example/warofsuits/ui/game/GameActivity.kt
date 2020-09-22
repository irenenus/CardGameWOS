package com.example.warofsuits.ui.game

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.warofsuits.R
import com.example.warofsuits.model.Suit
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity: AppCompatActivity(), GameView {

    lateinit var presenter: GamePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        presenter = GamePresenterImpl(this, this)
        presenter.onCreateGame()

        onListeners()

    }

    private fun onListeners(){
        btnReplay1.setOnClickListener {
            cvLayDownCard1.visibility = View.INVISIBLE
            cvLayDownCard2.visibility = View.INVISIBLE
            ivDiscard1.visibility = View.INVISIBLE
            ivDiscard2.visibility = View.INVISIBLE

            presenter.onRestartGame()
        }

        btnReplay2.setOnClickListener {
            cvLayDownCard1.visibility = View.INVISIBLE
            cvLayDownCard2.visibility = View.INVISIBLE
            ivDiscard1.visibility = View.INVISIBLE
            ivDiscard2.visibility = View.INVISIBLE

            presenter.onRestartGame()
        }

        cvPile1.setOnClickListener {
            if(cvLayDownCard1.isVisible && cvLayDownCard2.isVisible) {
                cvLayDownCard1.visibility = View.INVISIBLE
                cvLayDownCard2.visibility = View.INVISIBLE
            }
            presenter.onLayDownCardPlayer1()
        }

        cvPile2.setOnClickListener {
            if(cvLayDownCard1.isVisible && cvLayDownCard2.isVisible) {
                cvLayDownCard1.visibility = View.INVISIBLE
                cvLayDownCard2.visibility = View.INVISIBLE
            }
            presenter.onLayDownCardPlayer2()
        }

    }

    private fun setWinner(){
        when(presenter.getWinner()){
            true -> {
                tvWinner1.text = getString(R.string.player1_winner)
                tvWinner2.text = getString(R.string.player1_winner)

                ivDiscard1.visibility = View.VISIBLE
            }
            false -> {
                tvWinner1.text = getString(R.string.player2_winner)
                tvWinner2.text = getString(R.string.player2_winner)

                ivDiscard2.visibility = View.VISIBLE
            }
        }
    }

    override fun onFinishGame(discardCounter1: Int, discardCounter2: Int) {
        finish()
    }

    override fun onBothLayDownDone(): Boolean {
        return cvLayDownCard1.isVisible && cvLayDownCard2.isVisible
    }

    override fun onLayDownCardPlayer1(drawableLayDownCardPlayer1: Drawable) {

        //Show cards of the pile of Player1
        cvLayDownCard1.visibility = View.VISIBLE
        imLayDownCard1.setImageDrawable(drawableLayDownCardPlayer1)

        setWinner()
    }

    override fun onLayDownCardPlayer2(drawableLayDownCardPlayer2: Drawable) {

        //Show cards of the pile of Player1
        cvLayDownCard2.visibility = View.VISIBLE
        imLayDownCard2.setImageDrawable(drawableLayDownCardPlayer2)

        setWinner()
    }

    override fun setActionDiscardCountText(numDiscardedCards1: Int, numDiscardedCards2: Int) {

        //Set in ui the number of discarded cards
        tvDiscard1.text = resources.getString(R.string.discarded_cards, numDiscardedCards1)
        tvDiscard2.text = resources.getString(R.string.discarded_cards, numDiscardedCards2)

    }

    override fun showSuitsOrder(suitList: MutableList<Suit>) {

        //Fill all the suits image view with a random order, indicating which is the the most valuable in a descending order
        val viewListPlayer1 : List<ImageView> = listOf(ivSuits1, ivSuits2, ivSuits3, ivSuits4)
        val viewListPlayer2 : List<ImageView> = listOf(ivSuits1_2, ivSuits2_2, ivSuits3_2, ivSuits4_2)

        suitList.forEach {
            viewListPlayer1[suitList.indexOf(it)].setImageDrawable(it.drawable)
            viewListPlayer2[suitList.indexOf(it)].setImageDrawable(it.drawable)
        }
    }

}
