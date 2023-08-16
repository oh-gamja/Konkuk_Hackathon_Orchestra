package com.example.ohgamja_frontend.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ohgamja_frontend.R
import com.google.android.material.animation.Positioning

class FilterRVAdapter (val items : MutableList<String>) : RecyclerView.Adapter<FilterRVAdapter.ViewHolder>(){
    
    var mPosition = 0

    fun getPosition() : Int {
        return mPosition
    }

    fun setPosition(position : Int){
        mPosition = position
    }

    fun addItem(it : String){
        items.add(it)
        notifyDataSetChanged()
    }

    fun removeItem(deleteit : String){
        val index = items.indexOf(deleteit)
        items.removeAt(index)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterRVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.filter_rv_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilterRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item : String){
            val rv_text = itemView.findViewById<TextView>(R.id.tagTitle)
            rv_text.text = item
        }
    }
}