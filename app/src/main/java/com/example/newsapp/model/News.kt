package com.example.newsapp.model

data class News(
    val id: Int,
    val name: String,
    val description: String,
    val image: Int,
    var isBookmark: Boolean = false
)
