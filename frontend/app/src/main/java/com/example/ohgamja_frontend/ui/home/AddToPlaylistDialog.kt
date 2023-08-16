package com.example.ohgamja_frontend.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ohgamja_frontend.databinding.DialogAddToPlaylistBinding

class AddToPlaylistDialog : DialogFragment() {

    private lateinit var binding : DialogAddToPlaylistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogAddToPlaylistBinding.inflate(inflater)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        val items = arrayListOf<AddToPlaylistViewModel>()

        items.add(AddToPlaylistViewModel("플레이리스트1",3))
        items.add(AddToPlaylistViewModel("플레이리스트2",4))
        items.add(AddToPlaylistViewModel("플레이리스트3",5))
        items.add(AddToPlaylistViewModel("플레이리스트4",6))
        items.add(AddToPlaylistViewModel("플레이리스트5",7))
        items.add(AddToPlaylistViewModel("플레이리스트6",8))
        items.add(AddToPlaylistViewModel("플레이리스트7",9))
        items.add(AddToPlaylistViewModel("플레이리스트8",10))
        items.add(AddToPlaylistViewModel("플레이리스트9",11))

        val Drv = binding.dialogListRv
        val AddToPlaylistAdapter = AddToPlaylistAdapter(items, requireContext())

        Drv.adapter = AddToPlaylistAdapter
        Drv.layoutManager = LinearLayoutManager(context)

        binding.cancelButton.setOnClickListener {
            //취소
            this.dismiss()
            Log.e("dialog","뒤로가기")
        }


        return binding.root
    }

}