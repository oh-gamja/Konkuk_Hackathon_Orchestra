package com.example.ohgamja_frontend.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ohgamja_frontend.databinding.FragmentLikeBinding
import com.example.ohgamja_frontend.ui.RVAdapter
import com.example.ohgamja_frontend.ui.RecyclerviewModel

class LikeFragment : Fragment() {
    lateinit var binding: FragmentLikeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLikeBinding.inflate(inflater, container, false)

        var items = mutableListOf<RecyclerviewModel>()

        /*//카카오 로그인 버튼 누를시 동작 구현
        val login_btn = binding.loginBtn
        login_btn.setOnClickListener {

        }*/

        items.add(
            RecyclerviewModel(
                "젠가",
                3,
                "스릴러",
                "4")
        )
        items.add(
            RecyclerviewModel(
                "젠가",
                3,
                "스릴러",
                "4")
        )
        items.add(
            RecyclerviewModel(
                "젠가",
                3,
                "스릴러",
                "4")
        )
        items.add(
            RecyclerviewModel(
                "젠가",
                3,
                "스릴러",
                "4")
        )
        items.add(
            RecyclerviewModel(
                "젠가",
                3,
                "스릴러",
                "4")
        )

        val rv = binding.likerv
        val rvAdapter = RVAdapter(this,requireContext(),items)

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

}