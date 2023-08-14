package com.example.ohgamja_frontend.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ohgamja_frontend.databinding.ActivityGameInfoBinding

class GameInfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGameInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameInfoBinding.inflate(layoutInflater)

        binding.addButton.setOnClickListener {

            val dialog = AddListDialogFragment()
            dialog.show(supportFragmentManager,"DialogFragment")

        }

        //데이터 불러오기
//        binding.gameName.text = "asdf"
//        binding.gameLevel1, 2, 3 -> 배열
//        binding.gameLevel1.visibility = View.GONE
//        binding.gameLevel1.visibility = View.VISIBLE
//        for(배열){
//            배열[i].visibility
//        }
//        binding.gameName.text = 3.toString()


        setContentView(binding.root)
    }
}