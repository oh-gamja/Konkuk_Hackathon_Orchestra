package com.example.ohgamja_frontend.ui.playlists

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.ItemPlaylistsAdapterBinding

class PlaylistsAdapter(
    private val context: Context,
    private val fragmentManager: FragmentManager,
    val items: ArrayList<PlaylistViewModel>
) : RecyclerView.Adapter<PlaylistsAdapter.ViewHolder>() {

    private lateinit var itemClickListener : OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick()
    }

    fun setOnItemClickListener(onItemClickListener : OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

    //playlist 불러오기
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsAdapter.ViewHolder {
        val binding =
            ItemPlaylistsAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    //전체 recyclerview의 갯수
    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: ItemPlaylistsAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PlaylistViewModel) {
            binding.root.setOnClickListener {
                val intent = Intent(context, PlaylistInfoActivity::class.java)
                intent.putExtra("playlistId", item.playlistId)
                intent.putExtra("playlistName",item.listName)
                context.startActivity(intent)
            }

            binding.rvListName.text = item.listName
            binding.rvGameNum.setText("${item.gameNum.toString()}개")
            binding.rvDelButton.setOnClickListener {
                if (item.trashStatus) {
                    val dialog =
                        DeletePlaylistDialog(item.playlistId)
                    dialog.show(fragmentManager, "DialogFragment")
                } else {
                    val intent = Intent(context, PlaylistInfoActivity::class.java)
                    intent.putExtra("playlistId", item.playlistId)
                    intent.putExtra("playlistName",item.listName)
                    context.startActivity(intent)
                }
            }
            if (item.trashStatus) {
                binding.rvDelButton.setImageResource(R.drawable.ic_trash_bin)
            } else {
                binding.rvDelButton.setImageResource(R.drawable.ic_right_arrow)
            }
        }
    }

    fun stateChange() {
        for (item in items) {
            item.trashStatus = !item.trashStatus
        }
    }

}
