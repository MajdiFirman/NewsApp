package com.example.newsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.News
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: NewsRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<News>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<News>>
        get() = _uiState

    fun getNewsById(id: Int) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        _uiState.value = UiState.Success(repository.getNewsById(id))
    }

    fun updateNews(id: Int, newState: Boolean) = viewModelScope.launch {
        repository.updateNews(id, !newState)
            .collect{ isUpdated ->
                if(isUpdated) getNewsById(id)
            }
    }
}