package com.example.warofsuits.ui.game

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
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
        onStartGame()

        onListeners()

    }

    private fun onListeners(){
        btnReplay1.setOnClickListener {
            //Try onStartGame but the layout not responds well when user clicks replay when they lay down cards
            recreate()
        }

        btnReplay2.setOnClickListener {
            //Try onStartGame but the layout not responds well when user clicks replay when they lay down cards

            recreate()
        }

        cvPile1.setOnClickListener {
            presenter.onLayDownCardPlayer1()
            cvPile1.isEnabled = false
        }

        cvPile2.setOnClickListener {
            presenter.onLayDownCardPlayer2()
            cvPile2.isEnabled = false
        }

    }

    private fun onStartGame(){
        presenter.onCreateGame()

        /* I try this instead of recreate but there was a lot of layout problems:

        imLayDownCard1.visibility = View.INVISIBLE
        imLayDownCard2.visibility = View.INVISIBLE
        ivDiscard1.visibility = View.INVISIBLE
        ivDiscard2.visibility = View.INVISIBLE
        cvPile1.isEnabled = true
        cvPile2.isEnabled = true*/

    }

    private fun setWinner() {
        when (presenter.getWinner()) {
            true -> showWinner(getString(R.string.player1_winner), ivDiscard1)

            false -> showWinner(getString(R.string.player2_winner), ivDiscard2)
        }
    }


    private fun showWinner(message: String, view: View){
        tvWinner1.visibility = View.VISIBLE
        tvWinner2.visibility = View.VISIBLE
        tvWinner1.text = message
        tvWinner2.text = message

        Handler().postDelayed(
            {
                tvWinner1.visibility = View.GONE
                tvWinner2.visibility = View.GONE
                imLayDownCard1.visibility = View.GONE
                imLayDownCard2.visibility = View.GONE
                view.visibility = View.VISIBLE
                cvPile1.isEnabled = true
                cvPile2.isEnabled = true
            },
            3000
        )
    }


    override fun onFinishGame(discardCounter1: Int, discardCounter2: Int) {
        // Make a finish for the moment
        finish()
    }

    override fun onBothLayDownDone(): Boolean {
        return imLayDownCard1.isVisible && imLayDownCard2.isVisible
    }

    override fun onLayDownCardPlayer1(drawableLayDownCardPlayer1: Drawable) {
        //Show cards of the pile of Player1
        imLayDownCard1.visibility = View.VISIBLE
        imLayDownCard1.setImageDrawable(drawableLayDownCardPlayer1)

        setWinner()
    }

    override fun onLayDownCardPlayer2(drawableLayDownCardPlayer2: Drawable) {
        //Show cards of the pile of Player1
        imLayDownCard2.visibility = View.VISIBLE
        imLayDownCard2.setImageDrawable(drawableLayDownCardPlayer2)

        setWinner()
    }

    override fun setActionDiscardCountText(numDiscardedCards1: Int, numDiscardedCards2: Int) {
        //Set and update in ui the number of discarded cards
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
