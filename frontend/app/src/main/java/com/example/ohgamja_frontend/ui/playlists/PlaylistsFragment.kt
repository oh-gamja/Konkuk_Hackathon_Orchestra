package com.example.ohgamja_frontend.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.FragmentHomeBinding
import com.example.ohgamja_frontend.databinding.FragmentLikeBinding
import com.example.ohgamja_frontend.databinding.FragmentPlaylistsBinding
import com.example.ohgamja_frontend.ui.RVAdapter_playList

class PlaylistsFragment : Fragment() {
    lateinit var binding: FragmentPlaylistsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaylistsBinding.inflate(inflater, container, false)

        val playListItem = LayoutInflater.from(context).inflate(R.layout.rv_playlist_item,null)

        val item = playListItem.findViewById<LinearLayout>(R.id.rv_playListItem)

        val delete_btn = binding.deleteBtn
        var click_count = false

        var dialog_deleteItem = LayoutInflater.from(context).inflate(R.layout.dialog_delete_item,null)

        var mBuilder_deleteItem = AlertDialog.Builder(context)
            .setView(dialog_deleteItem)

        delete_btn.setOnClickListener {
            if(click_count == false){
                delete_btn.setText("취소")
                click_count = true
            }
            else if(click_count == true){
                delete_btn.setText("리스트삭제")
                click_count = false
            }
        }


        var items = mutableListOf<String>()
        items.add("바니바니")
        items.add("당근당근")
        items.add("마피아")
        items.add("아파트")
        items.add("눈치게임")

        val rv = binding.rvPlayList
        val rvAdapter = RVAdapter_playList(items)

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context)

        rvAdapter.itemClick = object : RVAdapter_playList.ItemClick{
            override fun onClick(view: View, position: Int) {
                if(click_count == true){
                    mBuilder_deleteItem.show()
                }
            }
        }

        return binding.root
    }

}