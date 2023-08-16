package com.example.ohgamja_frontend.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ContentInfoCompat.Flags
import com.example.ohgamja_frontend.MainActivity
import com.example.ohgamja_frontend.ui.retrofit.LoginRequest
import com.example.ohgamja_frontend.ui.retrofit.LoginResponse
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
import com.example.ohgamja_frontend.ui.retrofit.saveAccessToken
import com.example.ohgamja_frontend.ui.retrofit.saveEmail
import com.example.ohgamja_frontend.databinding.FragmentKakaoLoginBinding
import com.example.ohgamja_frontend.ui.retrofit.BaseResponse
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KakaoLoginFragment : Fragment() {
    lateinit var binding: FragmentKakaoLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentKakaoLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vKakaoLogin.setOnClickListener {
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e("LOGIN", "카카오계정으로 로그인 실패", error)
                } else if (token != null) {
                    Log.i("LOGIN", "카카오계정으로 로그인 성공 ${token.idToken}")
                    requestKakaoLogin(token)
                }
            }

            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
                UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                    if (error != null) {
                        Log.e("LOGIN", "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(
                            requireContext(),
                            callback = callback
                        )
                    } else if (token != null) {
                        Log.i("LOGIN", "카카오톡으로 로그인 성공 ${token.idToken}")
                        requestKakaoLogin(token)
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(
                    requireContext(),
                    callback = callback
                )
            }
        }
    }

    private fun requestKakaoLogin(token: OAuthToken) {
        val loginRequest = LoginRequest(token.idToken!!)

        RetrofitUtil.getLoginRetrofit().KaKaoLogin(loginRequest)
            .enqueue(object : Callback<BaseResponse<LoginResponse>>{
                override fun onResponse(
                    call: Call<BaseResponse<LoginResponse>>,
                    response: Response<BaseResponse<LoginResponse>>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()!!.result
                        val accessToken = result.accessToken
                        val email = result.email
                        Log.d("Retrofit", accessToken)

                        saveAccessToken(requireContext(), accessToken)
                        saveEmail(requireContext(), email)
                        RetrofitUtil.setAccessToken(accessToken)

                        val i = Intent(requireContext(), MainActivity::class.java)
                        val activity = requireActivity()
                        activity.startActivity(i)
                        activity.finish()
                    } else {
                        Log.d("Retrofit", response.message())
                    }
                }

                override fun onFailure(call: Call<BaseResponse<LoginResponse>>, t: Throwable) {
                    Log.d("Retrofit", t.message.toString())
                }
            })
    }
}