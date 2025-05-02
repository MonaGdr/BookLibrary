package com.example.booklibrary.domain.repo

import com.example.booklibrary.data.local.model.BookEntity
import com.example.booklibrary.domain.model.Book

interface BookRepo {
    suspend fun getBooksLocally(): List<BookEntity>
    suspend fun fetchBooksFromApi(page: Int): List<Book>
}