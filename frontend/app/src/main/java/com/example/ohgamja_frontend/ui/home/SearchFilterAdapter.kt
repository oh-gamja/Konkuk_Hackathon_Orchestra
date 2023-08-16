package com.example.ohgamja_frontend.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ohgamja_frontend.R

class SearchFilterAdapter (val items : MutableList<String>) : RecyclerView.Adapter<SearchFilterAdapter.ViewHolder>(){
    
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFilterAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_filter_adapter,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchFilterAdapter.ViewHolder, position: Int) {
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