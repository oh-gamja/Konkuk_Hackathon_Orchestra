package com.example.ohgamja_frontend.ui.playlists

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.ohgamja_frontend.R
import com.example.ohgamja_frontend.databinding.DialogDeletePlaylistBinding
import com.example.ohgamja_frontend.ui.retrofit.BaseResponse
import com.example.ohgamja_frontend.ui.retrofit.DeletePlaylistRequest
import com.example.ohgamja_frontend.ui.retrofit.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeletePlaylistDialog(val items : ArrayList<PlaylistViewModel>, val adapterPosition: Int, private val adapter: PlaylistsAdapter) : DialogFragment() {

    private lateinit var binding : DialogDeletePlaylistBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.dialog_delete_playlist, container, false)

        binding = DialogDeletePlaylistBinding.inflate(inflater)

        val cancelBtn: Button = rootView.findViewById(R.id.cancelBtn)
        cancelBtn.setOnClickListener {
            Toast.makeText(context, "delete Cancel", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        val deleteListBtn: Button = rootView.findViewById(R.id.deleteListBtn)
        deleteListBtn.setOnClickListener {
            Toast.makeText(context, "deleteList", Toast.LENGTH_SHORT).show()
            //플리 삭제됨
            items.removeAt(adapterPosition)
            adapter.notifyItemRemoved(adapterPosition)
            dismiss()
        }

        return rootView
    }

}