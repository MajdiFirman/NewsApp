package com.example.newsapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsapp.di.Injection
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.R
import com.example.newsapp.model.News
import com.example.newsapp.ui.common.UiState
import com.example.newsapp.ui.item.EmptyList
import com.example.newsapp.ui.viewmodel.BookmarkViewModel
import com.example.newsapp.ui.viewmodel.ViewModelFactory

@Composable
fun BookmarkScreen(
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getBookmarkNews()
            }
            is UiState.Success -> {
                BookmarkInformation(
                    listNews = uiState.data,
                    navigateToDetail = navigateToDetail,
                    onBookmarkIconClicked = {id, newState ->
                        viewModel.updateNews(id, newState)
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun BookmarkInformation(
    listNews : List<News>,
    navigateToDetail: (Int) -> Unit,
    onBookmarkIconClicked: (id:Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        if (listNews.isNotEmpty()){
            ListNews(
                listNews = listNews,
                onBookmarkIconClicked = onBookmarkIconClicked,
                contentPaddingTop = 16.dp,
                navigateToDetail = navigateToDetail
            )
        }else{
           EmptyList(Warning = stringResource(R.string.empty_bookmark) )
        }
    }
}