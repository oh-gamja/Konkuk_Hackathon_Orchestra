package com.example.ohgamja_frontend.ui.playlists

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.ohgamja_frontend.databinding.DialogAddPlaylistBinding
import com.example.ohgamja_frontend.ui.RVViewModel
import com.example.ohgamja_frontend.ui.retrofit.BaseResponse
import com.example.ohgamja_frontend.ui.retrofit.GamesResponse
import com.example.ohgamja_frontend.ui.retrofit.LoginRequest
import com.example.ohgamja_frontend.ui.retrofit.PlaylistRequest
import com.example.ohgamja_frontend.ui.retrofit.PlaylistResponse
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPlaylistDialog : DialogFragment() {

    private lateinit var binding : DialogAddPlaylistBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogAddPlaylistBinding.inflate(inflater)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)


        binding.button1.setOnClickListener {
            //취소
            this.dismiss()
        }

        binding.button2.setOnClickListener {
            val playlistRequest = PlaylistRequest(binding.addPlaylist.text.toString())

            //플리 추가됨
            RetrofitUtil.getRetrofit().AddList(playlistRequest)
                .enqueue(object : Callback<BaseResponse<String>> {
                    override fun onResponse(
                        call: Call<BaseResponse<String>>,
                        response: Response<BaseResponse<String>>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("Retrofit",binding.addPlaylist.text.toString())

                        } else {
                            Log.d("Retrofit", response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse<String>>, t: Throwable) {
                        Log.d("Retrofit", t.message.toString())
                    }
                })
            dismiss()
        }

        return binding.root
    }

}