package com.example.ohgamja_frontend.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.ohgamja_frontend.R

class DeleteDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.dialog_delete, container, false)

        val deleteCancelButton: Button = rootView.findViewById(R.id.deleteCancelBtn)
        deleteCancelButton.setOnClickListener {
            Toast.makeText(context, "delete Cancel", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        val deleteButton: Button = rootView.findViewById(R.id.deleteBtn)
        deleteButton.setOnClickListener {
            Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show()
            // 탈퇴 api 호출
        }

        return rootView
    }

}