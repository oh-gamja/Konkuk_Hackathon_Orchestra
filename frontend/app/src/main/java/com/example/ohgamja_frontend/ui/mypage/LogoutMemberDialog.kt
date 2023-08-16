package com.example.ohgamja_frontend.ui.mypage

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.ohgamja_frontend.R

class LogoutMemberDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.dialog_logout_member, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        val logoutCancelBtn: Button = rootView.findViewById(R.id.button1)
        logoutCancelBtn.setOnClickListener {
            Toast.makeText(context, "logout Cancel", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        val logoutBtn: Button = rootView.findViewById(R.id.button2)
        logoutBtn.setOnClickListener {
            Toast.makeText(context, "logout", Toast.LENGTH_SHORT).show()
            // 로그아웃 api 호출
        }

        return rootView
    }
}