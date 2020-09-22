package com.example.warofsuits.ui.game

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.warofsuits.R
import com.example.warofsuits.model.Card
import com.example.warofsuits.model.Suit

class CardsInteractor(val context: Context) {


    fun createCards() : MutableList<Card> {
        val cardsList = mutableListOf<Card>()

        //Fill the cards List getting from the integer array the drawables depending on the suits
        val hArray = context.resources.obtainTypedArray(R.array.integer_array_h)
        for (i in 2..14 step 1){
            val id = "h$i"
            cardsList.add(Card(id, i, 'H', ContextCompat.getDrawable(context, hArray.getResourceId(i-2, -1))!!))
        }
        hArray.recycle()

        val sArray = context.resources.obtainTypedArray(R.array.integer_array_s)
        for (i in 2..14 step 1){
            val id = "s$i"
            cardsList.add(Card(id, i, 'S', ContextCompat.getDrawable(context, sArray.getResourceId(i-2, -1))!!))
        }
        sArray.recycle()

        val dArray = context.resources.obtainTypedArray(R.array.integer_array_d)
        for (i in 2..14 step 1){
            val id = "d$i"
            cardsList.add(Card(id, i, 'D', ContextCompat.getDrawable(context, dArray.getResourceId(i-2, -1))!!))
        }
        dArray.recycle()

        val cArray = context.resources.obtainTypedArray(R.array.integer_array_c)
        for (i in 2..14 step 1){
            val id = "c$i"
            cardsList.add(Card(id, i, 'C', ContextCompat.getDrawable(context, cArray.getResourceId(i-2, -1))!!))
        }
        cArray.recycle()

        return cardsList
    }

    fun createSuits() : MutableList<Suit>{

        //Create a list of suits with their correspondent drawable
        return mutableListOf(
            Suit('S', ContextCompat.getDrawable(context, R.drawable.ic_spade)!!),
            Suit('D', ContextCompat.getDrawable(context, R.drawable.ic_diamond)!!),
            Suit('C', ContextCompat.getDrawable(context, R.drawable.ic_clubs)!!),
            Suit('H', ContextCompat.getDrawable(context, R.drawable.ic_heart)!!))

    }

}