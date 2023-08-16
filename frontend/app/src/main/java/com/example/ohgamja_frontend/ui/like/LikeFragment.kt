package com.example.ohgamja_frontend.ui.like

import android.net.DnsResolver.Callback
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ohgamja_frontend.databinding.FragmentLikeBinding
import com.example.ohgamja_frontend.ui.RVAdapter
import com.example.ohgamja_frontend.ui.RVViewModel
import com.example.ohgamja_frontend.ui.retrofit.BaseResponse
import com.example.ohgamja_frontend.ui.retrofit.GamesResponse
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
import com.example.ohgamja_frontend.ui.retrofit.TopGameResponse
import retrofit2.Call
import retrofit2.Response

class LikeFragment : Fragment() {
    lateinit var binding: FragmentLikeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLikeBinding.inflate(inflater, container, false)

        var items = mutableListOf<RVViewModel>()


        val rv = binding.likerv
        val rvAdapter = RVAdapter(0, requireContext(), childFragmentManager, items)

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context)

        RetrofitUtil.getRetrofit().GetLikes()
            .enqueue(object : retrofit2.Callback<BaseResponse<GamesResponse>> {
                override fun onResponse(
                    call: Call<BaseResponse<GamesResponse>>,
                    response: Response<BaseResponse<GamesResponse>>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()!!.result
                        val items = result.gamePreviews
                        items.forEach {
                            rvAdapter.items.add(
                                RVViewModel(it.gameId, it.gameImage, it.gameName, it.difficulty, it.category, it.headCount, it.likeCount, it.like)
                            )
                        }
                        rvAdapter.notifyDataSetChanged()
                    } else {
                        Log.d("Retrofit", response.message())
                    }
                }

                override fun onFailure(call: Call<BaseResponse<GamesResponse>>, t: Throwable) {
                    Log.d("Retrofit", t.message.toString())
                }
            })



        return binding.root
    }

}