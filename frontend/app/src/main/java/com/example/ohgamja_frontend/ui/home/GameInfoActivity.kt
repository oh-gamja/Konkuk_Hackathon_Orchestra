package com.example.ohgamja_frontend.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ohgamja_frontend.MainActivity
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.ActivityGameInfoBinding
import com.example.ohgamja_frontend.ui.AddToPlaylistDialog
import com.example.ohgamja_frontend.ui.retrofit.BaseResponse
import com.example.ohgamja_frontend.ui.retrofit.DetailRequest
import com.example.ohgamja_frontend.ui.retrofit.DetailResponse
import com.example.ohgamja_frontend.ui.retrofit.LoginRequest
import com.example.ohgamja_frontend.ui.retrofit.LoginResponse
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
import com.example.ohgamja_frontend.ui.retrofit.saveAccessToken
import com.example.ohgamja_frontend.ui.retrofit.saveEmail
import com.kakao.sdk.auth.model.OAuthToken
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    private fun getGameInfo(token: DetailRequest) {
        val detailRequest = DetailRequest(token.gameId!!)

        RetrofitUtil.getRetrofit().GetGameDetail(detailRequest).enqueue(object:
            Callback<BaseResponse<DetailResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<DetailResponse>>,
                response: Response<BaseResponse<DetailResponse>>
            ) {
                if(response.isSuccessful){
                    //데이터 처리
                    //
                } else {
                    //에러 코드
                }
            }

            override fun onFailure(call: Call<BaseResponse<DetailResponse>>, t: Throwable) {
                //ㅁ이라ㅓ짇차ㅡ
            }

        })

    }
}