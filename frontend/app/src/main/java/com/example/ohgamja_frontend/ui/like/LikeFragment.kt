package com.example.ohgamja_frontend.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ohgamja_frontend.databinding.FragmentLikeBinding

class LikeFragment : Fragment() {
    lateinit var binding: FragmentLikeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLikeBinding.inflate(inflater, container, false)
        //카카오 로그인 버튼 누를시 동작 구현
        val login_btn = binding.loginBtn
        login_btn.setOnClickListener {

        }

        return binding.root
    }

}