package com.example.ohgamja_frontend.ui.playlists

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.ItemPlaylistsAdapterBinding

class PlaylistsAdapter(private val context: Context, private val fragmentManager: FragmentManager, val items : ArrayList<PlaylistViewModel>) : RecyclerView.Adapter<PlaylistsAdapter.ViewHolder>() {

    //playlist 불러오기
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsAdapter.ViewHolder {
        val binding = ItemPlaylistsAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

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

    inner class ViewHolder(val binding: ItemPlaylistsAdapterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : PlaylistViewModel) {
            binding.rvListName.text = item.listName
            binding.rvGameNum.text = item.gameNum.toString()
            binding.rvDelButton.setOnClickListener {
                Toast.makeText(context, "delete!!", Toast.LENGTH_SHORT).show()

                if (item.trashStatus) {
                    val dialog = DeletePlaylistDialog(items, adapterPosition, this@PlaylistsAdapter)
                    dialog.show(fragmentManager,"DialogFragment")
                }
            }
            if (item.trashStatus) {
                binding.rvDelButton.setImageResource(R.drawable.ic_trash_bin)
            } else {
                binding.rvDelButton.setImageResource(R.drawable.ic_botnav_heart_empty)
            }
        }
    }

    fun stateChange() {
        for (item in items) {
            item.trashStatus = !item.trashStatus
        }
    }

}