package com.example.ohgamja_frontend.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ohgamja_frontend.MainActivity
import com.example.ohgamja_frontend.databinding.FragmentHomeBinding
import com.example.ohgamja_frontend.ui.AddToPlaylistDialog
import com.example.ohgamja_frontend.ui.RVAdapter
import com.example.ohgamja_frontend.ui.RVViewModel
import com.example.ohgamja_frontend.ui.retrofit.BaseResponse
import com.example.ohgamja_frontend.ui.retrofit.GamesResponse
import com.example.ohgamja_frontend.ui.retrofit.LoginResponse
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
import com.example.ohgamja_frontend.ui.retrofit.TopGameResponse
import com.example.ohgamja_frontend.ui.retrofit.saveAccessToken
import com.example.ohgamja_frontend.ui.retrofit.saveEmail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        var items = mutableListOf<RVViewModel>()

        val searchBar = binding.searchBar

        searchBar.setOnClickListener {
            val i = Intent(requireContext(), SearchActivity::class.java)
            startActivity(i)
        }

        val rv = binding.rv
        val rvAdapter = RVAdapter(0, requireContext(), childFragmentManager , items)

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        //추가 버튼 클릭 시 AddToPlayListDialog() 띄우기
        rvAdapter.itemClick = object : RVAdapter.ItemClick {
            override fun onClick() {
                val dialog = AddToPlaylistDialog()
                dialog.show(childFragmentManager, "DialogFragment")
            }
        }



        Log.d("qwerty123", "getallgames")
        RetrofitUtil.getRetrofit().GetAllGames()
            .enqueue(object : Callback<BaseResponse<GamesResponse>>{
                override fun onResponse(
                    call: Call<BaseResponse<GamesResponse>>,
                    response: Response<BaseResponse<GamesResponse>>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()!!.result
                        val items = result.gamePreviews
                        items.forEach {
                            rvAdapter.items.add(
                                RVViewModel(it.gameId, it.gameName, it.difficulty, it.category, it.headCount, it.likeCount)
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

        RetrofitUtil.getRetrofit().GetTopGameList().enqueue(object : Callback<BaseResponse<TopGameResponse>>{
            override fun onResponse(
                call: Call<BaseResponse<TopGameResponse>>,
                response: Response<BaseResponse<TopGameResponse>>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()!!.result
                    val topItems = result.likeTopGames
                    val info_intent = Intent(requireContext(),GameInfoActivity::class.java)
                    binding.tvTop1Title.setText(topItems[0].gameName)
                    binding.tvTop2Title.setText(topItems[1].gameName)
                    binding.tvTop3Title.setText(topItems[2].gameName)

                    binding.topItem1.setOnClickListener {
                        startActivity(info_intent)
                        info_intent.putExtra("gameId",topItems[0].gameId)
                    }
                    binding.topItem2.setOnClickListener {
                        startActivity(info_intent)
                        info_intent.putExtra("gameId",topItems[1].gameId)
                    }
                    binding.topItem3.setOnClickListener {
                        startActivity(info_intent)
                        info_intent.putExtra("gameId",topItems[2].gameId)
                    }

                    binding.tvTop1Num.setText(topItems[0].likeCount.toString())
                    binding.tvTop2Num.setText(topItems[1].likeCount.toString())
                    binding.tvTop3Num.setText(topItems[2].likeCount.toString())

                    Glide.with(requireContext())
                        .load(topItems[0].gameImage)
                        .into(binding.ivTop1)
                    Glide.with(requireContext())
                        .load(topItems[1].gameImage)
                        .into(binding.ivTop2)
                    Glide.with(requireContext())
                        .load(topItems[2].gameImage)
                        .into(binding.ivTop3)

                } else {
                    Log.d("Retrofit", response.message())
                }
            }

            override fun onFailure(call: Call<BaseResponse<TopGameResponse>>, t: Throwable) {
                Log.d("Retrofit", t.message.toString())
            }

        })



        return binding.root
    }

}