package com.example.ohgamja_frontend.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ohgamja_frontend.databinding.DialogAddToPlaylistBinding
import com.example.ohgamja_frontend.ui.retrofit.BaseResponse
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
import com.example.ohgamja_frontend.ui.retrofit.playlistInfoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddToPlaylistDialog(val gameId: Int) : DialogFragment() {

    private lateinit var binding : DialogAddToPlaylistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogAddToPlaylistBinding.inflate(inflater)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        val items = arrayListOf<AddToPlaylistViewModel>()

        val Drv = binding.dialogListRv
        val AddToPlaylistAdapter = AddToPlaylistAdapter(items, requireContext(), gameId)

        Drv.adapter = AddToPlaylistAdapter
        Drv.layoutManager = LinearLayoutManager(context)

        RetrofitUtil.getRetrofit().GetListInfo(gameId).enqueue(object: Callback<BaseResponse<playlistInfoList>>{
            override fun onResponse(
                call: Call<BaseResponse<playlistInfoList>>,
                response: Response<BaseResponse<playlistInfoList>>
            ) {
                if(response.isSuccessful){
                    val result = response.body()!!.result
                    result.playlistInfoList.forEach {
                        items.add(AddToPlaylistViewModel(it.playlistId, it.isInPlaylist, it.playlistName, it.gameCount))
                    }
                    AddToPlaylistAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BaseResponse<playlistInfoList>>, t: Throwable) {
            }

        })


        binding.cancelButton.setOnClickListener {
            //취소
            this.dismiss()
            Log.e("dialog","뒤로가기")
        }


        return binding.root
    }

}