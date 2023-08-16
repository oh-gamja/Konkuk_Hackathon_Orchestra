package com.example.ohgamja_frontend.ui.playlists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.ActivityPlaylistInfoBinding
import com.example.ohgamja_frontend.databinding.ActivitySearchBinding
import com.example.ohgamja_frontend.ui.RVAdapter
import com.example.ohgamja_frontend.ui.RVViewModel

class PlaylistInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPlaylistInfoBinding.inflate(layoutInflater)

        val items = mutableListOf<RVViewModel>()

        /*items.add(
            RVViewModel(
                "젠가",
                3,
                "스릴러",
                "4", 4
            )
        )
        items.add(
            RVViewModel(
                "경도",
                2,
                "범죄",
                "2", 4
            )
        )
        items.add(
            RVViewModel(
                "바니바니",
                2,
                "농락",
                "5", 4
            )
        )
        items.add(
            RVViewModel(
                "출석부",
                1,
                "속도",
                "6",
                4
            )
        )
        items.add(
            RVViewModel(
                "더 게임 오브 데스",
                3,
                "도박",
                "8",
                4
            )
        )*/

        val rvAdapter = RVAdapter(0, this, supportFragmentManager, items)

        binding.playlistRv.adapter = rvAdapter
        binding.playlistRv.layoutManager = LinearLayoutManager(this)




        setContentView(binding.root)

    }
}