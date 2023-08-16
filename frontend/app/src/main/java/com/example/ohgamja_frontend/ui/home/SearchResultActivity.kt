package com.example.ohgamja_frontend.ui.home

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.ActivitySearchResultBinding

class SearchResultActivity : AppCompatActivity() {
    lateinit var binding : ActivitySearchResultBinding
    var isInput = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchResultBinding.inflate(layoutInflater)

        var items = mutableListOf<String>()
        val rv = binding.recyclerView
        val mAdapter = SearchFilterAdapter(items)

        rv.adapter = mAdapter
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)


        binding.etSearch.setText(intent.getStringExtra("search"))
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

        binding.imageView.setOnClickListener { finish() }
        binding.textView4.setOnClickListener { finish() }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("texting","입력전")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("texting","입력중")
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("texting","입력끝")
                isInput = p0!!.isNotEmpty()
            }
        })

        binding.btnSearch.setOnClickListener {
            if (isInput) {
                var intent = Intent(this, SearchResultActivity::class.java)

                intent.putExtra("search", binding.etSearch.text.toString())

                for (i in 0 until items.size) intent.putExtra("$i", items[i])

                var num = items.size
                intent.putExtra("itemSize", num)

                startActivity(intent)
            }
        }


    }
}