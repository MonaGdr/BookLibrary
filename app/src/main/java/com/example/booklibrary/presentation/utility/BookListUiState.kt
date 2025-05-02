package com.example.booklibrary.presentation.utility

import com.example.booklibrary.domain.model.Book

sealed class BookListUiState {
    object Loading : BookListUiState()
    data class Success(val books: List<Book>) : BookListUiState()
    data class Error(val message: String) : BookListUiState()
}
