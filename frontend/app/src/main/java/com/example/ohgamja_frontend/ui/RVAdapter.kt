package com.example.ohgamja_frontend.ui

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.ItemRvadapterBinding
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker




class RVAdapter(val type: Int, val context: Context, val items : MutableList<RVViewModel>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        val binding = ItemRvadapterBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
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

    inner class ViewHolder(val binding: ItemRvadapterBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindItems(item : RVViewModel){
            val rv_title = binding.itemTitle
            val rv_like = binding.likeBtn
            val rv_list = binding.listBtn
            val rv_likeCount = binding.likeCount

            val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_to_playlist,null)
            val mBuilder = AlertDialog.Builder(context)
            rv_list.setOnClickListener {
                if(mDialogView.parent != null){
                    ((mDialogView.parent) as ViewGroup).removeView(mDialogView)
                }
                mBuilder.setView(mDialogView)
                mBuilder.show()
            }



            var likeChance = false

            rv_like.setOnClickListener {
                if(likeChance == false){
                    val lc_int =Integer.parseInt(rv_likeCount.text.toString())
                    rv_likeCount.setText("${lc_int+1}")
                    rv_like.setImageResource(R.drawable.ic_like_heart_filled)
                    likeChance = true
                    showToastMessage()
                }
                else if(likeChance == true){
                    val lc_int =Integer.parseInt(rv_likeCount.text.toString())
                    rv_likeCount.setText("${lc_int-1}")
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