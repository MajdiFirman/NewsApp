package com.example.newsapp.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Bookmark : Screen("bookmark")
    object Profile : Screen("profile")
    object DetailNews : Screen("home/{newsId}"){
        fun createRoute(newsId: Int) = "home/$newsId"
    }
}