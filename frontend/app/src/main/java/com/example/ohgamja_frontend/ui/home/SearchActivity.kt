package com.example.ohgamja_frontend.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchBinding.inflate(layoutInflater)

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

        var items = mutableListOf<String>()
        val rv = binding.recyclerView
        val mAdapter = SearchFilterAdapter(items)

        rv.adapter = mAdapter
        rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


        binding.imgBtn1.setOnClickListener {
            if(check1 == false){
                binding.imgBtn1.setImageResource(R.drawable.ic_radio_btn_checked)
                binding.category1.setTextColor(ContextCompat.getColor(this, R.color.pink_sub1))
                check1 = true
                mAdapter.addItem("카테고리1")
            }
            else {
                binding.imgBtn1.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category1.setTextColor(ContextCompat.getColor(this, R.color.white))
                check1 = false
                mAdapter.removeItem("카테고리1")
            }
        }
        binding.imgBtn2.setOnClickListener {
            if(check2 == false){
                binding.imgBtn2.setImageResource(R.drawable.ic_radio_btn_checked)
                binding.category2.setTextColor(ContextCompat.getColor(this, R.color.pink_sub1))
                check2 = true
                mAdapter.addItem("카테고리2")
            }
            else {
                binding.imgBtn2.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category2.setTextColor(ContextCompat.getColor(this, R.color.white))
                check2 = false
                mAdapter.removeItem("카테고리2")

            }
        }
        binding.imgBtn3.setOnClickListener {
            if(check3 == false){
                binding.imgBtn3.setImageResource(R.drawable.ic_radio_btn_checked)
                binding.category3.setTextColor(ContextCompat.getColor(this, R.color.pink_sub1))
                check3 = true
                mAdapter.addItem("카테고리3")
            }
            else {
                binding.imgBtn3.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category3.setTextColor(ContextCompat.getColor(this, R.color.white))
                check3 = false
                mAdapter.removeItem("카테고리3")
            }
        }
        binding.imgBtn4.setOnClickListener {
            if(check4 == false){
                binding.imgBtn4.setImageResource(R.drawable.ic_radio_btn_checked)
                binding.category4.setTextColor(ContextCompat.getColor(this, R.color.pink_sub1))
                check4 = true
                mAdapter.addItem("카테고리4")
            }
            else {
                binding.imgBtn4.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.category4.setTextColor(ContextCompat.getColor(this, R.color.white))
                check4 = false
                mAdapter.removeItem("카테고리4")
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
                check8 = true
                mAdapter.addItem("별하나")
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
                check9 = true
                mAdapter.addItem("별두개")

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
                check10 = true
                mAdapter.addItem("별세개")

            }
            else {
                binding.imgBtn10.setImageResource(R.drawable.ic_radio_btn_unchecked)
                binding.difficulty3.setTextColor(ContextCompat.getColor(this, R.color.white))
                check10 = false
                mAdapter.removeItem("별세개")

            }
        }





        setContentView(binding.root)





    }
}