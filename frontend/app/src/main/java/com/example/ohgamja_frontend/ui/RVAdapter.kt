package com.example.ohgamja_frontend.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.ItemRvadapterBinding
import com.example.ohgamja_frontend.ui.home.GameInfoActivity
import com.example.ohgamja_frontend.ui.retrofit.BaseResponse
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
import com.example.ohgamja_frontend.ui.retrofit.gameIdRequest
import com.example.ohgamja_frontend.ui.retrofit.resultResponse
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RVAdapter(val type: Int, val context: Context, val fragmentManger: FragmentManager, val items : MutableList<RVViewModel>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        val binding = ItemRvadapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    interface ItemClick {
        fun onClick()
    }
    var itemClick : ItemClick? = null

    override fun onBindViewHolder(holder: RVAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: ItemRvadapterBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindItems(item : RVViewModel){
            val rv_container = binding.itemContainer
            val rv_like = binding.likeBtn
            val rv_list = binding.listBtn
            val rv_likeCount = binding.likeCount

            if(type==1){
                binding.listBtn.setImageResource(R.drawable.ic_added_game_into_playlist)
            }

            //게임 타이틀 설정
            binding.itemTitle.text = item.itemTitle

            //이미지 설정
            Glide.with(context)
                .load(item.itemImg)
                .into(binding.rvImg)

            //난이도 설정
            if(item.itemDifficulty=="1"){
                binding.difficulty1.setImageResource(R.drawable.ic_star_filled)
                binding.difficulty2.setImageResource(R.drawable.ic_star_empty)
                binding.difficulty3.setImageResource(R.drawable.ic_star_empty)
            } else if(item.itemDifficulty=="2"){
                binding.difficulty1.setImageResource(R.drawable.ic_star_filled)
                binding.difficulty2.setImageResource(R.drawable.ic_star_filled)
                binding.difficulty3.setImageResource(R.drawable.ic_star_empty)
            } else if(item.itemDifficulty=="3"){
                binding.difficulty1.setImageResource(R.drawable.ic_star_filled)
                binding.difficulty2.setImageResource(R.drawable.ic_star_filled)
                binding.difficulty3.setImageResource(R.drawable.ic_star_filled)
            }

            //인원 수 설정
            binding.headCount.setText("${item.itemPersonnel}+")

            //카테고리 설정
            binding.itemTag.setText(item.itemCategory)

            //좋아요 수 설정
            binding.likeCount.setText(item.itemLikeCount.toString())

            //itemContainer 클릭 시 gameinfoactivity로 전환
            rv_container.setOnClickListener {
                val intent = Intent(context,GameInfoActivity::class.java)
                intent.putExtra("gameId", item.itemId)
                context.startActivity(intent)
            }

            binding.listBtn.setOnClickListener {
                itemClick?.onClick()
            }

            var likeChance = item.isLiked
            if(likeChance == false){
                rv_like.setImageResource(R.drawable.ic_like_heart_empty)
            }
            else if(likeChance == true){
                rv_like.setImageResource(R.drawable.ic_like_heart_filled)
            }


            rv_like.setOnClickListener {
                if(likeChance == false){
                    val lc_int =Integer.parseInt(rv_likeCount.text.toString())
                    rv_likeCount.setText("${lc_int+1}")
                    rv_like.setImageResource(R.drawable.ic_like_heart_filled)
                    likeChance = true
                    showToastMessage()
                    addLike(item)
                }
                else if(likeChance == true){
                    val lc_int =Integer.parseInt(rv_likeCount.text.toString())
                    rv_likeCount.setText("${lc_int-1}")
                    rv_like.setImageResource(R.drawable.ic_like_heart_empty)
                    likeChance = false
                    removeLike(item)
                }
            }

        }

        private fun removeLike(item: RVViewModel) {
            RetrofitUtil.getRetrofit().DeleteLike(item.itemId)
                .enqueue(object : Callback<BaseResponse<resultResponse>> {
                    override fun onResponse(
                        call: Call<BaseResponse<resultResponse>>,
                        response: Response<BaseResponse<resultResponse>>
                    ) {
                        if (response.isSuccessful) {

                        }
                    }

                    override fun onFailure(
                        call: Call<BaseResponse<resultResponse>>,
                        t: Throwable
                    ) {
                    }

                })
        }

        private fun addLike(item: RVViewModel) {
            RetrofitUtil.getRetrofit().AddLike(item.itemId)
                .enqueue(object : Callback<BaseResponse<resultResponse>> {
                    override fun onResponse(
                        call: Call<BaseResponse<resultResponse>>,
                        response: Response<BaseResponse<resultResponse>>
                    ) {
                        if (response.isSuccessful) {
                        }
                    }

                    override fun onFailure(
                        call: Call<BaseResponse<resultResponse>>,
                        t: Throwable
                    ) {
                    }
                })
        }
    }
    private fun showToastMessage() {
        CustomToast(context, "좋아요 목록에 추가되었습니다").show()
    }

}