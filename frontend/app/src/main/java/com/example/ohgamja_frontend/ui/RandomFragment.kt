package com.example.ohgamja_frontend.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.FragmentPlaylistsBinding
import com.example.ohgamja_frontend.databinding.FragmentRandomBinding
import com.example.ohgamja_frontend.ui.home.GameInfoActivity
import com.example.ohgamja_frontend.ui.playlists.PlaylistViewModel
import com.example.ohgamja_frontend.ui.playlists.PlaylistsAdapter
import com.example.ohgamja_frontend.ui.retrofit.BaseResponse
import com.example.ohgamja_frontend.ui.retrofit.DetailResponse
import com.example.ohgamja_frontend.ui.retrofit.PlaylistResponse
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
import com.example.ohgamja_frontend.ui.retrofit.resultResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomFragment : Fragment() {
    private lateinit var binding: FragmentRandomBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomBinding.inflate(inflater)



        RetrofitUtil.getRetrofit().GetRandomGame()
            .enqueue(object : Callback<BaseResponse<DetailResponse>> {
                override fun onResponse(
                    call: Call<BaseResponse<DetailResponse>>,
                    response: Response<BaseResponse<DetailResponse>>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()!!.result
                        binding.itemTitle.text = result.gameName
                        Glide.with(requireContext()).load(result.gameImage).into(binding.gameIv)
                        binding.likeCount.text = result.likeCount.toString()
                        binding.categoryTv.text = result.category
                        binding.headCountTv.text = result.headCount.toString() + '+'
                        binding.root.setOnClickListener {
                            val intent = Intent(context, GameInfoActivity::class.java)
                            intent.putExtra("gameId", result.gameId)
                            requireContext().startActivity(intent)
                        }
                        var isLiked = result.isLike
                        if(isLiked == false){
                            binding.likeBtn.setImageResource(R.drawable.ic_like_heart_empty)
                        }
                        else if(isLiked == true){
                            binding.likeBtn.setImageResource(R.drawable.ic_like_heart_filled)
                        }

                        binding.likeBtn.setOnClickListener {
                            if(isLiked == false){
                                val lc_int =Integer.parseInt(binding.likeCount.text.toString())
                                binding.likeCount.setText("${lc_int+1}")
                                binding.likeBtn.setImageResource(R.drawable.ic_like_heart_filled)
                                isLiked = true
                                showToastMessage()
                                addLike(result.gameId)
                            }
                            else if(isLiked == true){
                                val lc_int =Integer.parseInt(binding.likeCount.text.toString())
                                binding.likeCount.setText("${lc_int-1}")
                                binding.likeBtn.setImageResource(R.drawable.ic_like_heart_empty)
                                isLiked = false
                                removeLike(result.gameId)
                            }
                        }


                    } else {
                        Log.d("Retrofit", response.message())
                    }
                }

                override fun onFailure(call: Call<BaseResponse<DetailResponse>>, t: Throwable) {
                    Log.d("Retrofit", t.message.toString())
                }
            })

        return binding.root
    }

    private fun showToastMessage() {
        CustomToast(requireContext(), "좋아요 목록에 추가되었습니다").show()
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