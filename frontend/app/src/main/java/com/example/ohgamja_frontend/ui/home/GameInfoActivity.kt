package com.example.ohgamja_frontend.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.ActivityGameInfoBinding
import com.example.ohgamja_frontend.ui.AddToPlaylistDialog

class GameInfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGameInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameInfoBinding.inflate(layoutInflater)

        binding.addButton.setOnClickListener {
            //추가할 플레이리스트를 고르는 dialog
            val dialog = AddToPlaylistDialog()
            dialog.show(supportFragmentManager,"DialogFragment")
        }

        var joa = false

        binding.gameHeart.setOnClickListener {
            //좋아요 적용 및 취소

            if (joa == false){
                var currentNum = binding.heartNum.text.toString().toInt()

                binding.gameHeart.setImageResource(R.drawable.ic_like_heart_filled)
                binding.heartNum.setText((currentNum+1).toString())
                Toast.makeText(this, "좋아요 목록에 추가되었습니다", Toast.LENGTH_SHORT).show()
                joa = true
            } else {
                var currentNum = binding.heartNum.text.toString().toInt()

                binding.gameHeart.setImageResource(R.drawable.ic_like_heart_empty)
                binding.heartNum.setText((currentNum-1).toString())
                Toast.makeText(this, "좋아요 목록에서 삭제되었습니다", Toast.LENGTH_SHORT).show()

                joa = false
            }
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