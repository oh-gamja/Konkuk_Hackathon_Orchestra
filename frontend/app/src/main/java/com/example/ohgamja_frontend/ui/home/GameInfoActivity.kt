package com.example.ohgamja_frontend.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.ActivityGameInfoBinding
import com.example.ohgamja_frontend.ui.AddToPlaylistDialog
import com.example.ohgamja_frontend.ui.retrofit.BaseResponse
import com.example.ohgamja_frontend.ui.retrofit.DetailResponse
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
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

        binding.bacnBtn.setOnClickListener {
            finish()
        }

        getGameInfo()

        setContentView(binding.root)
    }

    private fun getGameInfo() {
        val gameId = intent.getIntExtra("gameId",-1)

        RetrofitUtil.getRetrofit().GetGameDetail(gameId).enqueue(object:
            Callback<BaseResponse<DetailResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<DetailResponse>>,
                response: Response<BaseResponse<DetailResponse>>
            ) {
                if(response.isSuccessful){
                    //데이터 처리
                    val result = response.body()!!.result

                    binding.gameName.setText(result.gameName)
                    binding.tag.setText(result.category)
                    binding.heartNum.setText(result.likeCount.toString())
                    binding.detailContent.setText(result.description)

                    Glide.with(this@GameInfoActivity)
                        .load(result.gameImage)
                        .into(binding.mainImage)

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