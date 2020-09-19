package com.example.warofsuits.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.warofsuits.R
import com.example.warofsuits.ui.game.GameActivity
import com.example.warofsuits.ui.onButtonShowPopupWindowClick
import kotlinx.android.synthetic.main.activity_welcome.*


class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        onListeners()

    }

    private fun onListeners(){
        btnPlay.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }

        btnRules.setOnClickListener {
            onButtonShowPopupWindowClick(btnRules)
        }

    }

}
