package com.example.ohgamja_frontend.ui.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.ActivitySearchResultBinding

class SearchResultActivity : AppCompatActivity() {
    lateinit var binding : ActivitySearchResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchResultBinding.inflate(layoutInflater)

        var items = mutableListOf<String>()
        val rv = binding.recyclerView
        val mAdapter = SearchFilterAdapter(items)

        rv.adapter = mAdapter
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

        val itemSize = intent.getIntExtra("itemSize",0)

        if (itemSize==0) {
            binding.tvFilter.text="선택한 필터가 없습니다."
            binding.tvFilter.setTextColor(ContextCompat.getColor(this, R.color.gray400))
        } else {
            for (i in 0 until itemSize){
                intent.getStringExtra("$i")?.let { mAdapter.addItem(it) }
            }
        }

        //검색 결과 조회 api

        //검색 결과 없을 때
        //binding.tvNoResult.visibility = View.VISIBLE

        setContentView(binding.root)

    }
}