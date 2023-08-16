package com.example.ohgamja_frontend.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ohgamja_frontend.databinding.FragmentMypageBinding
import com.example.ohgamja_frontend.ui.mypage.DeleteMemberDialog
import com.example.ohgamja_frontend.ui.mypage.LogoutMemberDialog
import com.example.ohgamja_frontend.ui.retrofit.getEmail

class MypageFragment : Fragment() {
    lateinit var binding: FragmentMypageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMypageBinding.inflate(inflater, container, false)

//        val dialog_logout = LayoutInflater.from(context).inflate(R.layout.dialog_logout,null)
//        val mBuilder_logout = AlertDialog.Builder(context)
//            .setView(dialog_logout)
//
//        val dialog_delete = LayoutInflater.from(context).inflate(R.layout.dialog_delete,null)
//        val mBuilder_delete = AlertDialog.Builder(context)
//            .setView(dialog_delete)

        val delete_btn = binding.deleteBtn
        val logout_btn = binding.logoutBtn

        binding.profileEmail.text=getEmail(requireContext())

        logout_btn.setOnClickListener {
            val dialogFragment = LogoutMemberDialog()
            dialogFragment.show(requireActivity().supportFragmentManager, "CustomDialog")
        }

        delete_btn.setOnClickListener {
            val dialogFragment = DeleteMemberDialog()
            dialogFragment.show(requireActivity().supportFragmentManager, "CustomDialog")
        }

        return binding.root
    }



}