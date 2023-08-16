package com.example.ohgamja_frontend.ui.playlists

data class PlaylistViewModel (
    var trashStatus : Boolean,  // false이면 기본, true이면 trash
    val listName : String,
    val gameNum : Int
    )