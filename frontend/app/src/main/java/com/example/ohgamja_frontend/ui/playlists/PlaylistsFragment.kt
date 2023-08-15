package com.example.ohgamja_frontend.ui.home

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.FragmentHomeBinding
import com.example.ohgamja_frontend.databinding.FragmentLikeBinding
import com.example.ohgamja_frontend.databinding.FragmentPlaylistsBinding
import com.example.ohgamja_frontend.ui.RVAdapter_playList

class PlaylistsFragment : Fragment() {
    lateinit var binding: FragmentPlaylistsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaylistsBinding.inflate(inflater, container, false)

        val playListItem = LayoutInflater.from(context)
            .inflate(R.layout.rv_playlist_item, null) // rv_playlist_item.xml 파일 뷰 설정
        val playList_listBtn = playListItem.findViewById<ImageView>(R.id.listBtnBase)

        val delete_btnBase = binding.deleteBtnBase //리스트 삭제 버튼
        val delete_btnChange = binding.deleteBtnChange
        var click_count = false //리스트 삭제 버튼 클릭 여부

        var dialog_deleteItem =
            LayoutInflater.from(context)
                .inflate(R.layout.dialog_delete_item, null) //dialog_delete_item.xml 파일 뷰 설정


        var mBuilder_deleteItem = AlertDialog.Builder(context)
            .setCancelable(false)


        val cancel_btn = dialog_deleteItem.findViewById<Button>(R.id.cancelBtn) //dialog의 취소버튼


        delete_btnBase.setOnClickListener {
            click_count = true
            delete_btnBase.visibility = View.GONE
            delete_btnChange.visibility = View.VISIBLE
            RVAdapter_playList.ViewHolder(requireView()).trashCanImage()
        }
        delete_btnChange.setOnClickListener {
            click_count = false
            delete_btnBase.visibility = View.VISIBLE
            delete_btnChange.visibility = View.GONE
            RVAdapter_playList.ViewHolder(requireView()).BaseImage()
        }


        var items = mutableListOf<String>()
        items.add("바니바니")
        items.add("당근당근")
        items.add("마피아")
        items.add("아파트")
        items.add("눈치게임")



        val rv = binding.rvPlayList
        val rvAdapter = RVAdapter_playList(items)

        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context)


        rvAdapter.itemClick = object : RVAdapter_playList.ItemClick {
            override fun onClick(view: View, position: Int) {
                if (click_count == true) {
                    if (dialog_deleteItem.parent != null) {
                        ((dialog_deleteItem.parent) as ViewGroup).removeView(dialog_deleteItem)
                    }
                    mBuilder_deleteItem.setView(dialog_deleteItem)
                    RVAdapter_playList.ViewHolder(view).setChangeBackGroundColor()
                    var mAlertDialog = mBuilder_deleteItem.show()
                    cancel_btn.setOnClickListener {
                        mAlertDialog.dismiss()
                        RVAdapter_playList.ViewHolder(view).setBaseBackGroundColor()
                    }
                }


            }
        }

        return binding.root
    }

}