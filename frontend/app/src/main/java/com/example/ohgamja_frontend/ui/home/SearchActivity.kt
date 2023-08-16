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
import com.example.ohgamja_frontend.databinding.ActivitySearchBinding
import com.kakao.sdk.common.KakaoSdk.init

class SearchActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchBinding
    var items = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchBinding.inflate(layoutInflater)

        var isInput = false

        var check1 = false
        var check2 = false
        var check3 = false
        var check4 = false

        var check5 = false
        var check6 = false
        var check7 = false

        var check8 = false
        var check9 = false
        var check10 = false

        val rv = binding.recyclerView
        val mAdapter = SearchFilterAdapter(items)

        rv.adapter = mAdapter
        rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        binding.btnSearch.setOnClickListener {
            if (isInput) {
                var intent = Intent(this, SearchResultActivity::class.java)

                intent.putExtra("name", binding.etSearch.text.toString())
                var category = ""
                if(check1){
                    category = "순발력"
                }else if(check2){
                    category = "두뇌"
                }else if(check3){
                    category = "아이엠그라운드"
                }else if(check4){
                    category = "기타"
                }

                var headCount = -1
                if(check5){
                    headCount = 3
                }else if(check6){
                    headCount = 4
                }else if(check7){
                    headCount = 5
                }

                var difficulty = ""
                if(check8){
                    difficulty="별한개"
                }else if(check9){
                    difficulty="별두개"
                }else if(check10) {
                    difficulty="별세개"
                }
                intent.putExtra("category", category)
                intent.putExtra("headCount", headCount)
                intent.putExtra("difficulty", difficulty)
                startActivity(intent)
            }
        }

        binding.imgBtn1.setOnClickListener {
            if(check1 == false){
                binding.imgBtn1.setImageResource(R.drawable.ic_radio_btn_checked)
                binding.category1.setTextColor(ContextCompat.getColor(this, R.color.pink_sub1))
                binding.imgBtn3.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category3.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.imgBtn2.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category2.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.imgBtn4.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category4.setTextColor(ContextCompat.getColor(this, R.color.white))
                mAdapter.addItem("순발력")
                check1 = true
                if(check3 == true) {
                    check3 = false
                    mAdapter.removeItem("아이엠그라운드")
                }
                if(check2 == true){
                    check2 = false
                    mAdapter.removeItem("두뇌")
                }
                if(check4 == true){
                    check4 = false
                    mAdapter.removeItem("기타")
                }
            }
            else {
                binding.imgBtn1.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category1.setTextColor(ContextCompat.getColor(this, R.color.white))
                check1 = false
                mAdapter.removeItem("순발력")
            }
        }
        binding.imgBtn2.setOnClickListener {
            if(check2 == false){
                binding.imgBtn2.setImageResource(R.drawable.ic_radio_btn_checked)
                binding.category2.setTextColor(ContextCompat.getColor(this, R.color.pink_sub1))
                binding.imgBtn3.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category3.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.imgBtn4.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category4.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.imgBtn1.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category1.setTextColor(ContextCompat.getColor(this, R.color.white))
                mAdapter.addItem("두뇌")
                check2 = true
                if(check3 == true) {
                    check3 = false
                    mAdapter.removeItem("아이엠그라운드")
                }
                if(check4 == true){
                    check4 = false
                    mAdapter.removeItem("기타")
                }
                if(check1 == true){
                    check1 = false
                    mAdapter.removeItem("순발력")
                }
            }
            else {
                binding.imgBtn2.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category2.setTextColor(ContextCompat.getColor(this, R.color.white))
                check2 = false
                mAdapter.removeItem("두뇌")
            }
        }
        binding.imgBtn3.setOnClickListener {
            if(check3 == false){
                binding.imgBtn3.setImageResource(R.drawable.ic_radio_btn_checked)
                binding.category3.setTextColor(ContextCompat.getColor(this, R.color.pink_sub1))
                binding.imgBtn4.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category4.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.imgBtn2.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category2.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.imgBtn1.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category1.setTextColor(ContextCompat.getColor(this, R.color.white))
                mAdapter.addItem("아이엠그라운드")
                check3 = true
                if(check4 == true) {
                    check4 = false
                    mAdapter.removeItem("기타")
                }
                if(check2 == true){
                    check2 = false
                    mAdapter.removeItem("두뇌")
                }
                if(check1 == true){
                    check1 = false
                    mAdapter.removeItem("순발력")
                }
            }
            else {
                binding.imgBtn3.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category3.setTextColor(ContextCompat.getColor(this, R.color.white))
                check3 = false
                mAdapter.removeItem("아이엠그라운드")
            }
        }
        binding.imgBtn4.setOnClickListener {
            if(check4 == false){
                binding.imgBtn4.setImageResource(R.drawable.ic_radio_btn_checked)
                binding.category4.setTextColor(ContextCompat.getColor(this, R.color.pink_sub1))
                binding.imgBtn3.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category3.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.imgBtn2.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category2.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.imgBtn1.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category1.setTextColor(ContextCompat.getColor(this, R.color.white))
                mAdapter.addItem("기타")
                check4 = true
                if(check3 == true) {
                    check3 = false
                    mAdapter.removeItem("아이엠그라운드")
                }
                if(check2 == true){
                    check2 = false
                    mAdapter.removeItem("두뇌")
                }
                if(check1 == true){
                    check1 = false
                    mAdapter.removeItem("순발력")
                }
            }
            else {
                binding.imgBtn4.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category4.setTextColor(ContextCompat.getColor(this, R.color.white))
                check4 = false
                mAdapter.removeItem("기타")
            }
        }

        binding.imgBtn5.setOnClickListener {
            if(check5 == false){
                binding.imgBtn5.setImageResource(R.drawable.ic_radio_btn_checked)
                binding.personnel1.setTextColor(ContextCompat.getColor(this, R.color.pink_sub1))
                binding.imgBtn6.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.personnel2.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.imgBtn7.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.personnel3.setTextColor(ContextCompat.getColor(this, R.color.white))
                mAdapter.addItem("3명이상")
                check5 = true
                if(check6 == true) {
                    check6 = false
                    mAdapter.removeItem("4명이상")
                }
                if(check7 == true){
                    check7 = false
                    mAdapter.removeItem("5명이상")
                }
            }
            else {
                binding.imgBtn5.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.personnel1.setTextColor(ContextCompat.getColor(this, R.color.white))
                check5 = false
                mAdapter.removeItem("3명이상")
            }
        }
        binding.imgBtn6.setOnClickListener {
            if(check6 == false){
                binding.imgBtn6.setImageResource(R.drawable.ic_radio_btn_checked)
                binding.personnel2.setTextColor(ContextCompat.getColor(this, R.color.pink_sub1))
                binding.imgBtn5.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.personnel1.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.imgBtn7.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.personnel3.setTextColor(ContextCompat.getColor(this, R.color.white))
                check6 = true

                mAdapter.addItem("4명이상")
                if(check5 == true) {
                    check5 = false
                    mAdapter.removeItem("3명이상")
                }
                if(check7 == true){
                    check7 = false
                    mAdapter.removeItem("5명이상")
                }
            }
            else {
                binding.imgBtn6.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.personnel2.setTextColor(ContextCompat.getColor(this, R.color.white))
                check6 = false
                mAdapter.removeItem("4명이상")
            }
        }
        binding.imgBtn7.setOnClickListener {
            if(check7 == false){
                binding.imgBtn7.setImageResource(R.drawable.ic_radio_btn_checked)
                binding.personnel3.setTextColor(ContextCompat.getColor(this, R.color.pink_sub1))
                binding.imgBtn6.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.personnel2.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.imgBtn5.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.personnel1.setTextColor(ContextCompat.getColor(this, R.color.white))
                check7 = true
                mAdapter.addItem("5명이상")
                if(check6 == true) {
                    check6 = false
                    mAdapter.removeItem("4명이상")
                }
                if(check5 == true){
                    check5 = false
                    mAdapter.removeItem("3명이상")
                }
            }
            else {
                binding.imgBtn7.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.personnel3.setTextColor(ContextCompat.getColor(this, R.color.white))
                check7 = false
                mAdapter.removeItem("5명이상")
            }
        }

        binding.imgBtn8.setOnClickListener {
            if(check8 == false){
                binding.imgBtn8.setImageResource(R.drawable.ic_radio_btn_checked)
                binding.difficulty1.setTextColor(ContextCompat.getColor(this, R.color.pink_sub1))
                binding.imgBtn9.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.difficulty2.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.imgBtn10.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.difficulty3.setTextColor(ContextCompat.getColor(this, R.color.white))
                check8 = true
                mAdapter.addItem("별하나")
                if(check9 == true) {
                    check9 = false
                    mAdapter.removeItem("별두개")
                }
                if(check10 == true){
                    check10 = false
                    mAdapter.removeItem("별세개")
                }
            }
            else {
                binding.imgBtn8.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.difficulty1.setTextColor(ContextCompat.getColor(this, R.color.white))
                check8 = false
                mAdapter.removeItem("별하나")
            }
        }
        binding.imgBtn9.setOnClickListener {
            if(check9 == false){
                binding.imgBtn9.setImageResource(R.drawable.ic_radio_btn_checked)
                binding.difficulty2.setTextColor(ContextCompat.getColor(this, R.color.pink_sub1))
                binding.imgBtn8.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.difficulty1.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.imgBtn10.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.difficulty3.setTextColor(ContextCompat.getColor(this, R.color.white))
                check9 = true
                mAdapter.addItem("별두개")
                if(check8 == true) {
                    check8 = false
                    mAdapter.removeItem("별하나")
                }
                if(check10 == true){
                    check10 = false
                    mAdapter.removeItem("별세개")
                }
            }
            else {
                binding.imgBtn9.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.difficulty2.setTextColor(ContextCompat.getColor(this, R.color.white))
                check9 = false
                mAdapter.removeItem("별두개")
            }
        }
        binding.imgBtn10.setOnClickListener {
            if(check10 == false){
                binding.imgBtn10.setImageResource(R.drawable.ic_radio_btn_checked)
                binding.difficulty3.setTextColor(ContextCompat.getColor(this, R.color.pink_sub1))
                binding.imgBtn8.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.difficulty1.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.imgBtn9.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.difficulty2.setTextColor(ContextCompat.getColor(this, R.color.white))
                check10 = true
                mAdapter.addItem("별세개")
                if(check8 == true) {
                    check8 = false
                    mAdapter.removeItem("별하나")
                }
                if(check9 == true){
                    check9 = false
                    mAdapter.removeItem("별두개")
                }
            }
            else {
                binding.imgBtn10.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.difficulty3.setTextColor(ContextCompat.getColor(this, R.color.white))
                check10 = false
                mAdapter.removeItem("별세개")
            }
        }

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

        setContentView(binding.root)

        binding.imageView.setOnClickListener { finish() }
        binding.textView4.setOnClickListener { finish() }

    }

}