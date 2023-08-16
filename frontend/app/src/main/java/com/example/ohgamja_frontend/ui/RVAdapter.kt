package com.example.ohgamja_frontend.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ohgamja_frontend.R
import org.w3c.dom.Text

class RVAdapter(val fragment: Fragment, val context: Context, val items : MutableList<RecyclerviewModel>, var mBulider : AlertDialog.Builder, var listvw : View) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gameblock,parent,false)

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
        fun bindItems(item : RecyclerviewModel){
            val rv_title = itemView.findViewById<TextView>(R.id.tv_gameblock_name)
            val rv_like = itemView.findViewById<ImageView>(R.id.iv_like_empty)
            val rv_list = itemView.findViewById<ImageView>(R.id.iv_list)

            var likeChance = false

            rv_list.setOnClickListener {
                mBulider.show()
            }


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