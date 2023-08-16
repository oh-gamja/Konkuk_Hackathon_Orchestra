package com.example.ohgamja_frontend.ui.playlists

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ohgamja_frontend.databinding.FragmentPlaylistsBinding
import com.example.ohgamja_frontend.ui.RVAdapter_playList

class PlaylistsFragment : Fragment() {

    private lateinit var binding : FragmentPlaylistsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPlaylistsBinding.inflate(inflater)

        val items = arrayListOf<PlaylistViewModel>()

        items.add(PlaylistViewModel("플레이리스트1",3))
        items.add(PlaylistViewModel("플레이리스트2",4))
        items.add(PlaylistViewModel("플레이리스트3",5))
        items.add(PlaylistViewModel("플레이리스트4",6))
        items.add(PlaylistViewModel("플레이리스트5",7))
        items.add(PlaylistViewModel("플레이리스트6",8))
        items.add(PlaylistViewModel("플레이리스트7",9))
        items.add(PlaylistViewModel("플레이리스트8",10))
        items.add(PlaylistViewModel("플레이리스트10",11))

        val rv = binding.playRv
        val playlistsAdapter = PlaylistsAdapter(items)

        rv.adapter = playlistsAdapter
        rv.layoutManager = LinearLayoutManager(context)

        binding.playlistAddButton.setOnClickListener {
            val dialog = NewListDialogFragment()
            dialog.show(requireActivity().supportFragmentManager,"DialogFragment")
        }



        return binding.root
    }

}