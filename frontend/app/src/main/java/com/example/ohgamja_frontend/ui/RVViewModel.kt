package com.example.ohgamja_frontend.ui

data class RVViewModel(
    var itemId: Int,
    var itemImg: String,
    var itemTitle: String = "",
    var itemDifficulty: String,
    var itemCategory: String = "",
    var itemPersonnel: Int,
    var itemLikeCount : Int
)