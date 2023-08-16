package com.example.ohgamja_frontend.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ohgamja_frontend.databinding.FragmentHomeBinding
import com.example.ohgamja_frontend.ui.RVAdapter
import com.example.ohgamja_frontend.ui.RVViewModel

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        var items = mutableListOf<RVViewModel>()

        val searchBar = binding.searchBar

        searchBar.setOnClickListener {
            val i = Intent(requireContext(), SearchActivity::class.java)
            startActivity(i)
        }



        items.add(
            RVViewModel(
                "젠가",
                3,
                "스릴러",
                "4"
                ,200
            )
        )
        items.add(
            RVViewModel(
                "경도",
                2,
                "범죄",
                "2",
                100
            )
        )
        items.add(
            RVViewModel(
                "바니바니",
                2,
                "농락",
                "5",
                300
            )
        )
        items.add(
            RVViewModel(
                "출석부",
                1,
                "속도",
                "6",
                50
            )
        )
        items.add(
            RVViewModel(
                "더 게임 오브 데스",
                3,
                "도박",
                "8",
                60
            )
        )
        val rv = binding.rv
        val rvAdapter = RVAdapter(0, requireContext(), items)

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context)

        rvAdapter.itemClick = object : RVAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(requireContext(),GameInfoActivity::class.java)
                startActivity(intent)
            }
        }

        return binding.root
    }

}