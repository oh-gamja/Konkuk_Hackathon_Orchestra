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

class RVAdapter(val fragment: Fragment, val context: Context, val items : MutableList<RecyclerviewModel>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item : RecyclerviewModel){
            val rv_title = itemView.findViewById<TextView>(R.id.rvItem)
            val rv_like = itemView.findViewById<ImageView>(R.id.likeBtn)
            val rv_list = itemView.findViewById<ImageView>(R.id.listBtn)



            var likeChance = false

            var anyArray = arrayOf<String>("1","2","3","4")

            var mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog,null)
            var mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
                .setNegativeButton("취소", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {

                    }
                })
                .setTitle("추가할 리스트를 선택하세요")
                .setItems(anyArray){
                        p0,p1 ->
                    Toast.makeText(context,"gd",Toast.LENGTH_LONG).show()
                }


            if(mDialogView != null) {
                mDialogView = null
            }
            mBuilder.setView(mDialogView)


            rv_list.setOnClickListener {
                val mAlertDialog = mBuilder.show()
            }




            rv_like.setOnClickListener {
                if(likeChance == false){
                    rv_like.setImageResource(R.drawable.ic_star_filled)
                    likeChance = true
                }
                else if(likeChance == true){
                    rv_like.setImageResource(R.drawable.ic_star_empty)
                    likeChance = false
                }
            }

            rv_title.text = item.itemTitle
        }
    }
}