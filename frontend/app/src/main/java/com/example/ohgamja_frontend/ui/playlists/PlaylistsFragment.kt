package com.example.ohgamja_frontend.ui.playlists

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ohgamja_frontend.databinding.FragmentPlaylistsBinding
import com.example.ohgamja_frontend.ui.retrofit.BaseResponse
import com.example.ohgamja_frontend.ui.retrofit.PlaylistResponse
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsFragment : Fragment() {

    private lateinit var binding: FragmentPlaylistsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPlaylistsBinding.inflate(inflater)

        val items = arrayListOf<PlaylistViewModel>()

//        items.add(PlaylistViewModel(false, "플레이리스트1",3))
//        items.add(PlaylistViewModel(false, "플레이리스트2",4))
//        items.add(PlaylistViewModel(false, "플레이리스트3",5))
//        items.add(PlaylistViewModel(false, "플레이리스트4",6))
//        items.add(PlaylistViewModel(false, "플레이리스트5",7))
//        items.add(PlaylistViewModel(false, "플레이리스트6",8))
//        items.add(PlaylistViewModel(false, "플레이리스트7",9))
//        items.add(PlaylistViewModel(false, "플레이리스트8",10))
//        items.add(PlaylistViewModel(false, "플레이리스트10",11))


        val rv = binding.playRv
        val playlistsAdapter =
            PlaylistsAdapter(requireContext(), requireActivity().supportFragmentManager, items)
        rv.adapter = playlistsAdapter
        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        RetrofitUtil.getRetrofit().GetPlaylist()
            .enqueue(object : Callback<BaseResponse<PlaylistResponse>> {
                override fun onResponse(
                    call: Call<BaseResponse<PlaylistResponse>>,
                    response: Response<BaseResponse<PlaylistResponse>>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()!!.result
                        val playItems = result.playlistList
                        items.clear()
                        if(playItems != null){
                            playItems.forEach {
                                items.add(
                                    PlaylistViewModel(it.playlistId,false,it.playlistName,it.gameCount)
                                )
                            }
                            playlistsAdapter.notifyDataSetChanged()

                        }
                        playlistsAdapter.notifyDataSetChanged()
                    } else {
                        Log.d("Retrofit", response.message())
                    }
                }

                override fun onFailure(call: Call<BaseResponse<PlaylistResponse>>, t: Throwable) {
                    Log.d("Retrofit", t.message.toString())
                }
            })

        playlistsAdapter.setOnItemClickListener(object : PlaylistsAdapter.OnItemClickListener{
            override fun onItemClick() {

            }
        })



        binding.playlistAddButton.setOnClickListener {
            val dialog = AddPlaylistDialog()
            dialog.show(requireActivity().supportFragmentManager, "DialogFragment")
        }

        binding.deleteListIv.setOnClickListener {
            playlistsAdapter.stateChange()
            binding.playRv.adapter?.notifyDataSetChanged()
        }

        binding.deleteListTv.setOnClickListener {
            playlistsAdapter.stateChange()
            binding.playRv.adapter?.notifyDataSetChanged()
        }



        return binding.root
    }

}