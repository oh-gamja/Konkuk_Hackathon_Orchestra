package com.example.ohgamja_frontend.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ohgamja_frontend.MainActivity
import com.example.ohgamja_frontend.databinding.FragmentHomeBinding
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



//        items.add(
//            RVViewModel(
//                "젠가",
//                3,
//                "스릴러",
//                "4"
//                ,200
//            )
//        )
//        items.add(
//            RVViewModel(
//                "경도",
//                2,
//                "범죄",
//                "2",
//                100
//            )
//        )
//        items.add(
//            RVViewModel(
//                "바니바니",
//                2,
//                "농락",
//                "5",
//                300
//            )
//        )
//        items.add(
//            RVViewModel(
//                "출석부",
//                1,
//                "속도",
//                "6",
//                50
//            )
//        )
//        items.add(
//            RVViewModel(
//                "더 게임 오브 데스",
//                3,
//                "도박",
//                "8",
//                60
//            )
//        )
        val rv = binding.rv
        val rvAdapter = RVAdapter(0, requireContext(), childFragmentManager , items)

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        rvAdapter.itemClick = object : RVAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(requireContext(),GameInfoActivity::class.java)
                startActivity(intent)
            }
        }


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
                    binding.tvTop1Title.setText(topItems[0].gameName)
                    binding.tvTop2Title.setText(topItems[1].gameName)
                    binding.tvTop3Title.setText(topItems[2].gameName)

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