package com.example.booklibrary.domain.repo

import com.example.booklibrary.domain.model.Book

interface BookRepo {
    suspend fun fetchBooks(page: Int): List<Book>
}