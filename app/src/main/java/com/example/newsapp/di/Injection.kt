package com.example.newsapp.di

import com.example.newsapp.repository.NewsRepository

object Injection {
    fun provideRepository(): NewsRepository{
        return NewsRepository.getInstance()
    }
}