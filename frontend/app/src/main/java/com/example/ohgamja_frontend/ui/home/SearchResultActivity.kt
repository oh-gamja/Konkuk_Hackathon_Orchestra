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
import com.example.ohgamja_frontend.ui.RVAdapter
import com.example.ohgamja_frontend.ui.RVViewModel
import com.example.ohgamja_frontend.ui.retrofit.BaseResponse
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
import com.example.ohgamja_frontend.ui.retrofit.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchResultBinding
    var isInput = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchResultBinding.inflate(layoutInflater)

        var items = mutableListOf<String>()
        val rv = binding.recyclerView

        var name = intent.getStringExtra("name")
        var category = intent.getStringExtra("category")
        var difficulty = intent.getStringExtra("difficulty")
        var headCount = intent.getIntExtra("headCount", -1)

        binding.etSearch.setText(name)
        if (category!!.isNotEmpty()) {
            items.add(category)
        }
        if (difficulty!!.isNotEmpty()) {
            items.add(difficulty)
        }
        if (headCount != -1) {
            items.add(headCount.toString() + "명이상")
        }

        val mAdapter = SearchFilterAdapter(items)

        rv.adapter = mAdapter
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        if (items.isEmpty()) {
            binding.tvFilter.text = "선택한 필터가 없습니다."
            binding.tvFilter.setTextColor(ContextCompat.getColor(this, R.color.gray400))
        }

        var searchDifficulty = when (difficulty) {
            "별한개" -> "1"
            "별두개" -> "2"
            "별세개" -> "3"
            else -> ""
        }

        var rvItems = mutableListOf<RVViewModel>()
        var rvAdapter = RVAdapter(0, this, supportFragmentManager, rvItems)
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = rvAdapter

        RetrofitUtil.getRetrofit().GetSearch(searchDifficulty, category, name!!, headCount)
            .enqueue(object : Callback<BaseResponse<SearchResponse>> {
                override fun onResponse(
                    call: Call<BaseResponse<SearchResponse>>,
                    response: Response<BaseResponse<SearchResponse>>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()!!.result
                        rvItems.clear()
                        result.gamePreviews.forEach {
                            rvItems.add(
                                RVViewModel(
                                    it.gameId,
                                    it.gameName,
                                    it.difficulty,
                                    it.category,
                                    it.headCount,
                                    it.likeCount
                                )
                            )
                        }
                        rvAdapter.notifyDataSetChanged()
                        if (rvItems.isEmpty()) {
                            binding.tvNoResult.visibility = View.VISIBLE
                        } else {
                            binding.tvNoResult.visibility = View.GONE
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse<SearchResponse>>, t: Throwable) {
                }

            })

        //검색 결과 없을 때


        binding.imageView.setOnClickListener { finish() }
        binding.textView4.setOnClickListener { finish() }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("texting", "입력전")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("texting", "입력중")
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("texting", "입력끝")
                isInput = p0!!.isNotEmpty()
            }
        })

        binding.btnSearch.setOnClickListener {
            var intent = Intent(this, SearchResultActivity::class.java)
            intent.putExtra("name", binding.etSearch.text.toString())
            intent.putExtra("category", category)
            intent.putExtra("headCount", headCount)
            intent.putExtra("difficulty", difficulty)
            startActivity(intent)
            finish()
        }
        setContentView(binding.root)

    }
}