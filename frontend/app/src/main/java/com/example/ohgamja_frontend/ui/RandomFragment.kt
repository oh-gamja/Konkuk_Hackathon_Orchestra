package com.example.ohgamja_frontend.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.ohgamja_frontend.databinding.FragmentPlaylistsBinding
import com.example.ohgamja_frontend.databinding.FragmentRandomBinding
import com.example.ohgamja_frontend.ui.home.GameInfoActivity
import com.example.ohgamja_frontend.ui.playlists.PlaylistViewModel
import com.example.ohgamja_frontend.ui.playlists.PlaylistsAdapter
import com.example.ohgamja_frontend.ui.retrofit.BaseResponse
import com.example.ohgamja_frontend.ui.retrofit.DetailResponse
import com.example.ohgamja_frontend.ui.retrofit.PlaylistResponse
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
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
}