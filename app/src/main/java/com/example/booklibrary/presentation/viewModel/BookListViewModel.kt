package com.example.booklibrary.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrary.domain.usecase.GetBooks
import com.example.booklibrary.presentation.utility.BookListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookListViewModel(private val getBooks: GetBooks) : ViewModel() {

    private val _uiState = MutableStateFlow<BookListUiState>(BookListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchBooks()
    }

    fun fetchBooks() {
        viewModelScope.launch {
            _uiState.value = BookListUiState.Loading
            try {
                val result = getBooks.invoke(1)
                _uiState.value = BookListUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = BookListUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
