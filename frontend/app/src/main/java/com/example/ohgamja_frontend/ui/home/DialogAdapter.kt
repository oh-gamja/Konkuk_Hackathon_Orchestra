package com.example.ohgamja_frontend.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ohgamja_frontend.databinding.ItemDialogPlaylistsBinding

class DialogAdapter(val items : ArrayList<PlaylistViewModel>) : RecyclerView.Adapter<DialogAdapter.ViewHolder>() {

    //playlist 불러오기
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogAdapter.ViewHolder {
        val binding = ItemDialogPlaylistsBinding.inflate(LayoutInflater.from(parent.context))
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

    inner class ViewHolder(val binding: ItemDialogPlaylistsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item : PlaylistViewModel) {
            binding.DrvListName.text = item.listName
            binding.DrvButton.setOnClickListener {
                //items.removeAt(adapterPosition)
                //this@RVAdapter.notifyItemRemoved(adapterPosition)
                //아이콘(DrvButton) 바뀜 + 플리(item)에서 해당 게임 삭제
            }
        }

    }

}