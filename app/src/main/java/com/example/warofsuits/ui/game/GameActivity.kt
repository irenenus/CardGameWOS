package com.example.warofsuits.ui.game

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
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
            presenter.onRestartGame()
        }

        btnReplay1.setOnClickListener {
            presenter.onRestartGame()
        }
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
