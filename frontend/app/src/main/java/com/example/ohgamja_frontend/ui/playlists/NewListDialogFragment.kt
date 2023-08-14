package com.example.ohgamja_frontend.ui.playlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
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

        binding.button1.setOnClickListener {
            //플리 추가됨
            binding.editText.text
        }

        return binding.root
    }

}