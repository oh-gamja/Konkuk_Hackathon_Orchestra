package com.example.ohgamja_frontend.ui.playlists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.ActivityPlaylistInfoBinding
import com.example.ohgamja_frontend.databinding.ActivitySearchBinding
import com.example.ohgamja_frontend.ui.RVAdapter
import com.example.ohgamja_frontend.ui.RecyclerviewModel

class PlaylistInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPlaylistInfoBinding.inflate(layoutInflater)

        val items = mutableListOf<RecyclerviewModel>()

        items.add(
            RecyclerviewModel(
                "젠가",
                3,
                "스릴러",
                "4")
        )
        items.add(
            RecyclerviewModel(
                "경도",
                2,
                "범죄",
                "2")
        )
        items.add(
            RecyclerviewModel(
                "바니바니",
                2,
                "농락",
                "5")
        )
        items.add(
            RecyclerviewModel(
                "출석부",
                1,
                "속도",
                "6")
        )
        items.add(
            RecyclerviewModel(
                "더 게임 오브 데스",
                3,
                "도박",
                "8")
        )

        val rvAdapter = RVAdapter(Fragment(),this, items)

        binding.playlistRv.adapter = rvAdapter
        binding.playlistRv.layoutManager = LinearLayoutManager(this)




        setContentView(binding.root)

    }
}