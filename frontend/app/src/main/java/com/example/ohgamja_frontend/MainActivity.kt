package com.example.ohgamja_frontend

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
import com.example.ohgamja_frontend.databinding.ActivityMainBinding
import com.example.ohgamja_frontend.ui.KakaoLoginFragment
import com.example.ohgamja_frontend.ui.home.HomeFragment
import com.example.ohgamja_frontend.ui.home.MypageFragment
import com.example.ohgamja_frontend.ui.like.LikeFragment
import com.example.ohgamja_frontend.ui.playlists.PlaylistsFragment
import com.kakao.sdk.common.util.Utility

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, HomeFragment())
            .commitAllowingStateLoss()

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, HomeFragment()).commit()
                R.id.navigation_like -> {
                    if(RetrofitUtil.getAccessToken().isEmpty()){
                        supportFragmentManager.beginTransaction().replace(R.id.main_frame, KakaoLoginFragment()).commit()
                    } else {
                        supportFragmentManager.beginTransaction().replace(R.id.main_frame, LikeFragment()).commit()
                    }
                }
                R.id.navigation_random -> {
                    if(RetrofitUtil.getAccessToken().isEmpty()){
                        supportFragmentManager.beginTransaction().replace(R.id.main_frame, KakaoLoginFragment()).commit()
                    } else {
                        supportFragmentManager.beginTransaction().replace(R.id.main_frame, LikeFragment()).commit()
                    }
                }
                //게임추천 api로 변경 예정
                R.id.navigation_playlists -> {
                    if(RetrofitUtil.getAccessToken().isEmpty()){
                        supportFragmentManager.beginTransaction().replace(R.id.main_frame, KakaoLoginFragment()).commit()
                    } else {
                        supportFragmentManager.beginTransaction().replace(R.id.main_frame, PlaylistsFragment()).commit()
                    }
                }
                else-> {
                    if(RetrofitUtil.getAccessToken().isEmpty()){
                        supportFragmentManager.beginTransaction().replace(R.id.main_frame, KakaoLoginFragment()).commit()
                    } else {
                        supportFragmentManager.beginTransaction().replace(R.id.main_frame, MypageFragment()).commit()
                    }
                }
            }
            true
        }

    }
}