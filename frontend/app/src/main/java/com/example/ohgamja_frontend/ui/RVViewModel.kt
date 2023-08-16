package com.example.ohgamja_frontend.ui

data class RVViewModel(
    var itemId: Int,
    var itemTitle: String = "",
    var itemDifficulty: String,
    var itemCategory: String = "",
    var itemPersonnel: Int,
    var itemLikeCount : Int
)