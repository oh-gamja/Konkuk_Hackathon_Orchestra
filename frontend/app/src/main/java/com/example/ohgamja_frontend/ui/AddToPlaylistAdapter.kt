package com.example.ohgamja_frontend.ui

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.ItemDialogAddToPlaylistAdapterBinding

class AddToPlaylistAdapter(val items : ArrayList<AddToPlaylistViewModel>, val context: Context) : RecyclerView.Adapter<AddToPlaylistAdapter.ViewHolder>() {

    //playlist 불러오기
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDialogAddToPlaylistAdapterBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    interface OnItemClicked {
        fun onItemClicked()
    }

    //전체 recyclerview의 갯수
    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: ItemDialogAddToPlaylistAdapterBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bindItems(item : AddToPlaylistViewModel) {
            binding.DrvListName.text = item.listName

            var added = false

            binding.DrvContainer.setOnClickListener {

                if (added == false){
                    //아이콘(DrvButton) 바뀜
                    binding.DrvButton.setImageResource(R.drawable.ic_playlist_remove)
                    binding.DrvButton.imageTintList = ColorStateList.valueOf(context.getColor(R.color.lavender_sub1))
                    binding.DrvListName.setTextColor(ColorStateList.valueOf(context.getColor(R.color.lavender_sub1)))

                    //플리(item)에서 해당 게임 삭제 -> 아직 구현 못함 ㅎ

                    added = true
                } else {
                    //아이콘(DrvButton) 바뀜
                    binding.DrvButton.setImageResource(R.drawable.ic_add_to_playlist)
                    binding.DrvButton.imageTintList = ColorStateList.valueOf(context.getColor(R.color.white))
                    binding.DrvListName.setTextColor(ColorStateList.valueOf(context.getColor(R.color.gray400)))

                    //플리(item)에 해당 게임 추가 -> 이것도 ㅎ

                    added = false
                }


            }
        }

    }

}