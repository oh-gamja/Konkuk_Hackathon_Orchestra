package com.example.ohgamja_frontend.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ohgamja_frontend.MainActivity
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.FragmentHomeBinding
import com.example.ohgamja_frontend.ui.ListviewAdapter
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

        var listvw = LayoutInflater.from(requireContext()).inflate(R.layout.dialog,null)


        val list_item = mutableListOf<String>()

        list_item.add("내가 즐겨하는 게임")
        list_item.add("여자친구가 좋아하는 게임")
        list_item.add("원우가 좋아하는 게임")

        val listAdapter = ListviewAdapter(list_item)

        val listview = listvw.findViewById<ListView>(R.id.mainListView)

        listview.adapter = listAdapter

        val mBuilder = AlertDialog.Builder(requireContext())
            .setView(listvw)

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
        val rv = binding.rv
        val rvAdapter = RVAdapter(this,requireContext(),items, mBuilder,listvw)
        val mainActivity = activity as MainActivity

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(activity)

        rvAdapter.itemClick = object : RVAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

            }
            private fun check(){
                if(listvw != null){
                    listvw = null
                }
            }
        }

        return binding.root
    }

}