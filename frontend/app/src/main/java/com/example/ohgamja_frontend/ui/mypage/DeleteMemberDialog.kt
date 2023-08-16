package com.example.ohgamja_frontend.ui.mypage

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.ui.KakaoLoginActivity
import com.example.ohgamja_frontend.ui.RVViewModel
import com.example.ohgamja_frontend.ui.retrofit.BaseResponse
import com.example.ohgamja_frontend.ui.retrofit.GamesResponse
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
import com.example.ohgamja_frontend.ui.retrofit.SignoutResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteMemberDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.dialog_delete_member, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        val deleteCancelButton: Button = rootView.findViewById(R.id.button1)
        deleteCancelButton.setOnClickListener {
            Toast.makeText(context, "delete Cancel", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        val deleteButton: Button = rootView.findViewById(R.id.button2)
        deleteButton.setOnClickListener {
            Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show()
            // 탈퇴 api 호출
            RetrofitUtil.getRetrofit().KaKaoSingout()
                .enqueue(object : Callback<BaseResponse<SignoutResponse>> {
                    override fun onResponse(
                        call: Call<BaseResponse<SignoutResponse>>,
                        response: Response<BaseResponse<SignoutResponse>>
                    ) {
                        if (response.isSuccessful) {
                            val result = response.body()!!.result
                            val id = result.memberId
                            Log.d("Retrofit",id.toString())
                        } else {
                            Log.d("Retrofit", response.message())
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse<SignoutResponse>>, t: Throwable) {
                        Log.d("Retrofit", t.message.toString())
                    }
                })
            val intent = Intent(requireContext(),KakaoLoginActivity::class.java)
            startActivity(intent)
        }

        return rootView
    }

}