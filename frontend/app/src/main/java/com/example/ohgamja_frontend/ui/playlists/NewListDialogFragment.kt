package com.example.ohgamja_frontend.ui.playlists

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.FragmentNewListDialogBinding

class NewListDialogFragment : DialogFragment() {

    private lateinit var binding : FragmentNewListDialogBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewListDialogBinding.inflate(inflater)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)


        binding.button1.setOnClickListener {
            //취소
            this.dismiss()
        }

        binding.button2.setOnClickListener {
            //플리 추가됨
        }

        return binding.root
    }

}