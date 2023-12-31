package com.example.ohgamja_frontend

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ohgamja_frontend.databinding.ActivityMainBinding
import com.example.ohgamja_frontend.ui.RandomFragment
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
            when (it.itemId) {
                R.id.navigation_home -> supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame, HomeFragment()).commit()

                R.id.navigation_like ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, LikeFragment()).commit()

                R.id.navigation_random ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, RandomFragment()).commit()
                //게임추천 api로 변경 예정
                R.id.navigation_playlists ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, PlaylistsFragment()).commit()

                else ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, MypageFragment()).commit()
            }
            true
        }

    }
}