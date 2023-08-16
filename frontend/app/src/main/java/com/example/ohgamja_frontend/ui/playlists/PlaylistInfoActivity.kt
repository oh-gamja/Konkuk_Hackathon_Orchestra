package com.example.ohgamja_frontend.ui.playlists

import android.net.DnsResolver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.ActivityPlaylistInfoBinding
import com.example.ohgamja_frontend.databinding.ActivitySearchBinding
import com.example.ohgamja_frontend.ui.RVAdapter
import com.example.ohgamja_frontend.ui.RVViewModel
import com.example.ohgamja_frontend.ui.retrofit.BaseResponse
import com.example.ohgamja_frontend.ui.retrofit.GamesResponse
import com.example.ohgamja_frontend.ui.retrofit.PlaylistResponse
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlaylistInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPlaylistInfoBinding.inflate(layoutInflater)

        val items = mutableListOf<RVViewModel>()

        val rvAdapter = RVAdapter(1, this, supportFragmentManager, items)

        binding.playlistRv.adapter = rvAdapter
        binding.playlistRv.layoutManager = LinearLayoutManager(this)


        val playlistId = intent.getIntExtra("playlistId", -1)
        RetrofitUtil.getRetrofit().GetPlaylistDetail(playlistId)
            .enqueue(object : Callback<BaseResponse<GamesResponse>> {
                override fun onResponse(
                    call: Call<BaseResponse<GamesResponse>>,
                    response: Response<BaseResponse<GamesResponse>>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()!!.result
                        val items = result.gamePreviews
                        items.forEach {
                            rvAdapter.items.add(
                                RVViewModel(it.gameId,it.gameImage, it.gameName, it.difficulty, it.category, it.headCount, it.likeCount, it.like)
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

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.backBtnTv.setOnClickListener {
            finish()
        }


        setContentView(binding.root)

    }
}