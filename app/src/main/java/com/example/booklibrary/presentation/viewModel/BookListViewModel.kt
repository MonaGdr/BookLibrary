package com.example.booklibrary.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrary.data.NetworkChecker
import com.example.booklibrary.domain.usecase.GetBooks
import com.example.booklibrary.presentation.utility.BookListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookListViewModel(
    private val getBooks: GetBooks,
    private val networkChecker: NetworkChecker
) : ViewModel() {

    private val _uiState = MutableStateFlow<BookListUiState>(BookListUiState.Loading())
    val uiState = _uiState.asStateFlow()

    private var currentPage = 1
    private var isLastPage = false
    private var isLoading = false
    private var isOfflineMode = false

    fun fetchNextPage() {
        if (isLoading || isLastPage) return

        isLoading = true

        viewModelScope.launch {
            try {
                val isOnline = networkChecker.isOnline()

                if (!isOnline && isOfflineMode) {
                    isLoading = false
                    return@launch
                }

                val result = getBooks.invoke(currentPage)

                if (result.isEmpty()) {
                    isLastPage = true
                } else {
                    val currentData = (_uiState.value as? BookListUiState.Success)?.data ?: emptyList()
                    _uiState.value = BookListUiState.Success(currentData + result)
                    currentPage++
                }

                if (!isOnline) isOfflineMode = true
                else isOfflineMode = false

            } catch (e: Exception) {
                _uiState.value = BookListUiState.Error(e.message ?: "Unknown error")
            } finally {
                isLoading = false
            }
        }
    }
}
