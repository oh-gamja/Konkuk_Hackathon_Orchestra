package com.example.ohgamja_frontend.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.ohgamja_frontend.MainActivity
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.FragmentMypageBinding
import com.example.ohgamja_frontend.databinding.FragmentPlaylistsBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class MypageFragment : Fragment() {
    lateinit var binding: FragmentMypageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMypageBinding.inflate(inflater, container, false)

        val dialog_logout = LayoutInflater.from(context).inflate(R.layout.dialog_logout,null)
        val mBuilder_logout = AlertDialog.Builder(context)
            .setView(dialog_logout)

        val dialog_delete = LayoutInflater.from(context).inflate(R.layout.dialog_delete,null)
        val mBuilder_delete = AlertDialog.Builder(context)
            .setView(dialog_delete)

        val delete_btn = binding.deleteBtn
        val logout_btn = binding.logoutBtn

        logout_btn.setOnClickListener {
            mBuilder_logout.show()
        }

        delete_btn.setOnClickListener {
            mBuilder_delete.show()
        }

        return binding.root
    }

    private fun addKakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("LOGIN", "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.i("LOGIN", "카카오계정으로 로그인 성공 ${token.accessToken}")
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
                    Log.i("LOGIN", "카카오톡으로 로그인 성공 ${token.accessToken}")
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
        }
    }

}