package com.example.ohgamja_frontend.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ohgamja_frontend.databinding.FragmentAddListDialogBinding

class AddListDialogFragment : DialogFragment() {

    private lateinit var binding : FragmentAddListDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddListDialogBinding.inflate(inflater)

        val items = arrayListOf<PlaylistViewModel>()

        items.add(PlaylistViewModel("플레이리스트1",3))
        items.add(PlaylistViewModel("플레이리스트2",4))
        items.add(PlaylistViewModel("플레이리스트3",5))
        items.add(PlaylistViewModel("플레이리스트4",6))
        items.add(PlaylistViewModel("플레이리스트5",7))
        items.add(PlaylistViewModel("플레이리스트6",8))
        items.add(PlaylistViewModel("플레이리스트7",9))
        items.add(PlaylistViewModel("플레이리스트8",10))
        items.add(PlaylistViewModel("플레이리스트9",11))

        val Drv = binding.dialogRv
        val DialogAdapter = DialogAdapter(items)

        Drv.adapter = DialogAdapter
        Drv.layoutManager = LinearLayoutManager(context)

        binding.cancelButton.setOnClickListener {
            //취소
        }

        return binding.root
    }

}