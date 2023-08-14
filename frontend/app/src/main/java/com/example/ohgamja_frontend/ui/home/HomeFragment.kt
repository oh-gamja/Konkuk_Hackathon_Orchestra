package com.example.ohgamja_frontend.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ohgamja_frontend.MainActivity
import com.example.ohgamja_frontend.databinding.FragmentHomeBinding
import com.example.ohgamja_frontend.ui.RVAdapter
import com.example.ohgamja_frontend.ui.RecyclerviewModel

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        var items = mutableListOf<RecyclerviewModel>()

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
        //1231232131
        val rv = binding.rv
        val rvAdapter = RVAdapter(this,requireContext(),items)
        val mainActivity = activity as MainActivity

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(activity)

        return binding.root
    }

}