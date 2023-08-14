package com.example.ohgamja_frontend.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ohgamja_frontend.R

class RVAdapter_playList(val items : MutableList<String>) : RecyclerView.Adapter<RVAdapter_playList.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter_playList.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_playlist_item,parent,false)
        return ViewHolder(view)
    }

    interface ItemClick {
        fun onClick(view:View, position: Int)
    }
    var itemClick : ItemClick? = null

    override fun onBindViewHolder(holder: RVAdapter_playList.ViewHolder, position: Int) {
        if(itemClick != null){
            holder.itemView.setOnClickListener { v->
                itemClick?.onClick(v,position)
            }
        }
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindItems(item:String){
            val rv_text = itemView.findViewById<TextView>(R.id.gameListTitle)
            rv_text.text = item
        }
    }
}