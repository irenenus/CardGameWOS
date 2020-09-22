package com.example.warofsuits.ui

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import android.widget.RelativeLayout
import com.example.warofsuits.R


const val WIDTH = 900
const val HEIGHT = 1900

 fun Activity.onButtonShowPopupWindowClick(view: View) {
    // inflate the layout of the popup window
    val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val popupView = inflater.inflate(R.layout.popup_rules_window, RelativeLayout(view.context), false)

    // create the popup window
    val focusable = true
    val popupWindow = PopupWindow(popupView, WIDTH, HEIGHT, focusable)

    // show the popup window
    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

    // dismiss the popup window when close button is clicked
    val close = popupView.findViewById(R.id.btnClose) as Button
    close.setOnClickListener {
        popupWindow.dismiss()
    }
}