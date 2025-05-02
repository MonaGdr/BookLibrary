package com.example.booklibrary.presentation.utility

import com.example.booklibrary.domain.model.Book

sealed class BookListUiState(
    var data: List<Book>? = null,
    val message: String? = null
) {
    class Loading : BookListUiState()
    class Success( books: List<Book>) : BookListUiState(data = books)
    class Error( errorMessage: String) : BookListUiState(message = errorMessage)
}

