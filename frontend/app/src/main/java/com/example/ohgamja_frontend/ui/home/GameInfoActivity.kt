package com.example.ohgamja_frontend.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.ActivityGameInfoBinding
import com.example.ohgamja_frontend.ui.AddToPlaylistDialog
import com.example.ohgamja_frontend.ui.CustomToast
import com.example.ohgamja_frontend.ui.RVViewModel
import com.example.ohgamja_frontend.ui.retrofit.BaseResponse
import com.example.ohgamja_frontend.ui.retrofit.DetailResponse
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
import com.example.ohgamja_frontend.ui.retrofit.resultResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameInfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGameInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameInfoBinding.inflate(layoutInflater)


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

                    binding.addButton.setOnClickListener {
                        //추가할 플레이리스트를 고르는 dialog
                        val dialog = AddToPlaylistDialog(result.gameId)
                        dialog.show(supportFragmentManager,"DialogFragment")
                    }

                    var isLiked = result.isLike
                    if(isLiked == false){
                        binding.gameHeart.setImageResource(R.drawable.ic_like_heart_empty)
                    }
                    else if(isLiked == true){
                        binding.gameHeart.setImageResource(R.drawable.ic_like_heart_filled)
                    }

                    binding.gameHeart.setOnClickListener {
                        if(isLiked == false){
                            val lc_int =Integer.parseInt(binding.heartNum.text.toString())
                            binding.heartNum.setText("${lc_int+1}")
                            binding.gameHeart.setImageResource(R.drawable.ic_like_heart_filled)
                            isLiked = true
                            showToastMessage()
                            addLike(result.gameId)
                        }
                        else if(isLiked == true){
                            val lc_int =Integer.parseInt(binding.heartNum.text.toString())
                            binding.heartNum.setText("${lc_int-1}")
                            binding.gameHeart.setImageResource(R.drawable.ic_like_heart_empty)
                            isLiked = false
                            removeLike(result.gameId)
                        }
                    }

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

    private fun showToastMessage() {
        CustomToast(this, "좋아요 목록에 추가되었습니다").show()
    }

    private fun removeLike(gameId: Int) {
        RetrofitUtil.getRetrofit().DeleteLike(gameId)
            .enqueue(object : Callback<BaseResponse<resultResponse>> {
                override fun onResponse(
                    call: Call<BaseResponse<resultResponse>>,
                    response: Response<BaseResponse<resultResponse>>
                ) {
                    if (response.isSuccessful) {

                    }
                }

                override fun onFailure(
                    call: Call<BaseResponse<resultResponse>>,
                    t: Throwable
                ) {
                }

            })
    }

    private fun addLike(gameId: Int) {
        RetrofitUtil.getRetrofit().AddLike(gameId)
            .enqueue(object : Callback<BaseResponse<resultResponse>> {
                override fun onResponse(
                    call: Call<BaseResponse<resultResponse>>,
                    response: Response<BaseResponse<resultResponse>>
                ) {
                    if (response.isSuccessful) {
                    }
                }

                override fun onFailure(
                    call: Call<BaseResponse<resultResponse>>,
                    t: Throwable
                ) {
                }
            })
    }
}