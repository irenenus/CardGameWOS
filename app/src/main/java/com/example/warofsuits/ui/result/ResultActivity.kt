package com.example.warofsuits.ui.result

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.warofsuits.R
import com.example.warofsuits.model.Result
import com.example.warofsuits.ui.game.GameActivity
import kotlinx.android.synthetic.main.activity_result.*

const val RESULT = "RESULT"

class ResultActivity: AppCompatActivity() {

    private lateinit var result : Result
    lateinit var presenter: ResultPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        presenter = ResultPresenterImpl(this)
        result = intent.getSerializableExtra(RESULT) as Result

        setTexts(result)

        onListeners()

    }

    private fun setTexts(result: Result){
        //set text of number of discarded cards of each player
        tvNumDiscard1.text = result.discardCounter1.toString()
        tvNumDiscard2.text = result.discardCounter2.toString()

        //set text of number of rounds played for each player
        tvNumRounds1.text = result.numRounds1.toString()
        tvNumRounds2.text = result.numRounds2.toString()

        //set text of total rounds played
        tvNumTotalRounds.text = result.totalRounds.toString()

        tvResultWinner.text = presenter.getStringWinner(result.winner1)
    }

    private fun onListeners(){

        btnReplay.setOnClickListener {
            //start GameActivity to play again
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}