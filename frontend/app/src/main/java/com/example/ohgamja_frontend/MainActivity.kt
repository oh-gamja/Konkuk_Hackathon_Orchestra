package com.example.ohgamja_frontend

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.ohgamja_frontend.databinding.ActivityMainBinding
import com.example.ohgamja_frontend.ui.home.HomeFragment
import com.example.ohgamja_frontend.ui.home.LikeFragment
import com.example.ohgamja_frontend.ui.home.MypageFragment
import com.example.ohgamja_frontend.ui.home.PlaylistsFragment
import com.kakao.sdk.common.util.Utility

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, HomeFragment()).commit()
                R.id.navigation_like -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, LikeFragment()).commit()
                R.id.navigation_playlists -> supportFragmentManager.beginTransaction().replace(R.id.main_frame, PlaylistsFragment()).commit()
                else-> supportFragmentManager.beginTransaction().replace(R.id.main_frame, MypageFragment()).commit()
            }
            true
        }

        setContentView(binding.root)
    }
}