package com.example.ohgamja_frontend.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.ohgamja_frontend.R

class ListviewAdapter(val List : MutableList<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return List.size
    }

    override fun getItem(p0: Int): Any {
        return List[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var convertView = p1
        if(convertView == null){
            convertView = LayoutInflater.from(p2?.context).inflate(R.layout.listview_item,p2,false)
        }

        var title = convertView!!.findViewById<TextView>(R.id.title)
        title.text = List[p0]

        return convertView!!
    }
}