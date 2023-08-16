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

class DeleteMemberDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.dialog_delete_member, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        val deleteCancelButton: Button = rootView.findViewById(R.id.button1)
        deleteCancelButton.setOnClickListener {
            Toast.makeText(context, "delete Cancel", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        val deleteButton: Button = rootView.findViewById(R.id.button2)
        deleteButton.setOnClickListener {
            Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show()
            // 탈퇴 api 호출
        }

        return rootView
    }

}