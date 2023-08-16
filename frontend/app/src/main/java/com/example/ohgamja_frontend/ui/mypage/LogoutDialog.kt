package com.example.ohgamja_frontend.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.ohgamja_frontend.R

class LogoutDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.dialog_logout, container, false)

        val logoutCancelBtn: Button = rootView.findViewById(R.id.logoutCancelBtn)
        logoutCancelBtn.setOnClickListener {
            Toast.makeText(context, "logout Cancel", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        val logoutBtn: Button = rootView.findViewById(R.id.logoutBtn)
        logoutBtn.setOnClickListener {
            Toast.makeText(context, "logout", Toast.LENGTH_SHORT).show()
            // 로그아웃 api 호출
        }

        return rootView
    }
}