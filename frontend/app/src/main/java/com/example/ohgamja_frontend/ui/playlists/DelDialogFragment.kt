package com.example.ohgamja_frontend.ui.playlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.ohgamja_frontend.databinding.FragmentDelDialogBinding

class DelDialogFragment : DialogFragment() {

    private lateinit var binding : FragmentDelDialogBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDelDialogBinding.inflate(inflater)

        binding.button2.setOnClickListener {
            //플리 삭제됨
        }

        return binding.root
    }

}