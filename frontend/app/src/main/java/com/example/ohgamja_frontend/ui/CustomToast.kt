package com.example.ohgamja_frontend.ui

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.example.ohgamja_frontend.R

class CustomToast(context: Context, message: String) : Toast(context) {

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.toast_custom, null)
        view.findViewById<TextView>(R.id.text_toast).apply {
            text = message
        }
        setView(view)
        duration = Toast.LENGTH_SHORT
    }
}