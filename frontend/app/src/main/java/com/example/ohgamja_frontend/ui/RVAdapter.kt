package com.example.ohgamja_frontend.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ohgamja_frontend.R

class RVAdapter(val type: Int, val context: Context, val items : MutableList<RVViewModel>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rvadapter,parent,false)
        return ViewHolder(view)
    }
    interface ItemClick {
        fun onClick(view : View, position: Int)
    }
    var itemClick : ItemClick? = null

    override fun onBindViewHolder(holder: RVAdapter.ViewHolder, position: Int) {
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

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item : RVViewModel){
            val rv_title = itemView.findViewById<TextView>(R.id.itemTitle)
            val rv_like = itemView.findViewById<ImageView>(R.id.likeBtn)
            val rv_list = itemView.findViewById<ImageView>(R.id.listBtn)

            var likeChance = false

            rv_like.setOnClickListener {
                if(likeChance == false){
                    rv_like.setImageResource(R.drawable.ic_like_heart_filled)
                    likeChance = true
                    showToastMessage()
                }
                else if(likeChance == true){
                    rv_like.setImageResource(R.drawable.ic_like_heart_empty)
                    likeChance = false
                }
            }

            rv_title.text = item.itemTitle
        }
    }
    private fun showToastMessage() {
        CustomToast(context, "좋아요 목록에 추가되었습니다").show()
    }

}