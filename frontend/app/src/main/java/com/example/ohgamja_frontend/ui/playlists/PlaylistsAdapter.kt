package com.example.ohgamja_frontend.ui.playlists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ohgamja_frontend.databinding.ItemPlaylistsBinding

class PlaylistsAdapter(val items : ArrayList<PlaylistViewModel>) : RecyclerView.Adapter<PlaylistsAdapter.ViewHolder>() {

    //playlist 불러오기
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsAdapter.ViewHolder {
        val binding = ItemPlaylistsBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }


    interface OnItemClicked {
        fun onItemClicked()
    }


    override fun onBindViewHolder(holder: PlaylistsAdapter.ViewHolder, position: Int) {

        holder.bindItems(items[position])
    }

    //전체 recyclerview의 갯수
    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: ItemPlaylistsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item : PlaylistViewModel) {
            binding.rvListName.text = item.listName
            binding.rvGameNum.text = item.gameNum.toString()
            binding.rvDelButton.setOnClickListener {
                items.removeAt(adapterPosition)
                this@PlaylistsAdapter.notifyItemRemoved(adapterPosition)

                val dialog = DelDialogFragment()
                //dialog.show(this.supportFragmentManager,"DialogFragment")
            }
        }

    }

}